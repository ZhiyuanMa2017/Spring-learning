package com.squirrel.swagger.controller;

import com.squirrel.swagger.config.BaseResult;
import com.squirrel.swagger.model.Message;
import com.squirrel.swagger.repository.MessageRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "MessageController")
@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // Get all
    @Operation(summary = "message lists",
            description = "all messages",
            responses = {
                    @ApiResponse(content =
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)))
            })
    @GetMapping("/messages")
    public List<Message> list() {
        return this.messageRepository.findAll();
    }

    // New one
    @Operation(summary = "Add message",
            description = "Create message based on params")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "message id",
                    required = true, dataType = "Long", dataTypeClass = Long.class,
                    example = "1.0", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "content",
                    required = true, dataType = "String", dataTypeClass = String.class,
                    paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "abstract",
                    required = false, dataType = "String", dataTypeClass = String.class,
                    paramType = "query")})
    @PostMapping("/message")
    public Message create(Message message) {
        System.out.println("message====" + message.toString());
        message = this.messageRepository.save(message);
        return message;
    }

    // Use put
    @Operation(summary = "modify message", description = "modify message based on params",
            responses = {
                    @ApiResponse(
                            responseCode = "100",
                            description = "params wrong"
                    ),
                    @ApiResponse(
                            responseCode = "101",
                            description = "not authorized"
                    ),
                    @ApiResponse(
                            responseCode = "103",
                            description = "prohibit query"
                    ),
                    @ApiResponse(
                            responseCode = "104",
                            description = "path not exist"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "inner mistake"
                    )})
    @PutMapping("/message")
    public Message modify(Message message) {
        return this.messageRepository.update(message);
    }

    // Update text
    @PatchMapping("/message/text")
    public BaseResult<Message> patch(Message message) {
        return BaseResult.successWithData(this.messageRepository.updateText(message));
    }

    // Get by id
    @GetMapping("/message/{id}")
    public Message get(@PathVariable Long id) {
        return this.messageRepository.findMessage(id);
    }

    // Delete by id
    @DeleteMapping("/message/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
    }
}
