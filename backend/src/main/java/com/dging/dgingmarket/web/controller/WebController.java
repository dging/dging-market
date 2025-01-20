package com.dging.dgingmarket.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/html")
@RequiredArgsConstructor
public class WebController {

    @GetMapping("/addr")
    public String address() {
        return "addr";
    }

    @GetMapping("/chat-rooms/{roomId}")
    public String chatRoom(@PathVariable Long roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "chat/room";
    }
}
