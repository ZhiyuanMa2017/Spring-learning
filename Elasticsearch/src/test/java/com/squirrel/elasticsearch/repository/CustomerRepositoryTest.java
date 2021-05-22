package com.squirrel.elasticsearch.repository;

import com.squirrel.elasticsearch.model.Customer;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void testSave() {
        customerRepository.save(new Customer("abc", "北京", 10));
        customerRepository.save(new Customer("def", "北京123", 11));
        customerRepository.save(new Customer("hij", "456北京", 12));
    }

    @Test
    public void fetchAllCustomers() {
        Iterable<Customer> iterable = customerRepository.findAll();
        for (Customer customer : iterable) {
            System.out.println(customer);
        }
    }

    @Test
    public void deleteCustomers() {
        customerRepository.deleteAll();
    }

    @Test
    public void updateCustomer() {
        Customer customer = customerRepository.findByUserName("abc");
        System.out.println(customer);
        customer.setAddress("SZ");
        customerRepository.save(customer);
        Customer customer1 = customerRepository.findByUserName("abc");
        System.out.println(customer1);
    }

    @Test
    public void fetchCustomer() {
        String q = "北京";
        for (Customer customer : customerRepository.findByAddress(q)) {
            System.out.println(customer);
        }
    }

    @Test
    public void fetchPageCustomers() {
        Sort sort;
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "address.keyword"));
        Page<Customer> customers = customerRepository.findByAddress("北京", pageable);
        System.out.println(customers.getContent());
    }

    @Test
    public void fetchPageCustomers2() {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("address", "北京"));
        NativeSearchQuery nativeSearchQuery =
                new NativeSearchQueryBuilder()
                        .withQuery(queryBuilder)
                        .build();
        SearchHits<Customer> searchHits =
                elasticsearchRestTemplate.search(nativeSearchQuery, Customer.class);
        SearchPage<Customer> page = SearchHitSupport
                .searchPageFor(searchHits, PageRequest.of(0, 10));
        for (SearchHit<Customer> searchHit : page.getSearchHits()) {
            System.out.println(searchHit.getContent());
        }
        System.out.println(page.getSearchHits().getSearchHits());
    }

    @Test
    public void fetchAggregation() {
        // Query condition
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("address", "北京"));
        // Aggregation field
        SumAggregationBuilder sumAggregationBuilder =
                AggregationBuilders.sum("sumAge").field("age");
        // Construct Search Query
        NativeSearchQuery nativeSearchQuery =
                new NativeSearchQueryBuilder()
                        .withQuery(queryBuilder)
                        .addAggregation(sumAggregationBuilder)
                        .build();
        // Search
        SearchHits<Customer> searchHits =
                elasticsearchRestTemplate.search(nativeSearchQuery, Customer.class);
        // Get field result
        Sum sum = searchHits.getAggregations().get("sumAge");
        System.out.println(sum.getValue());
    }
}
