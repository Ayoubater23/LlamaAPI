package com.llm.demoforllm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/llama")
public class LlamaController {

    private final LlamaService llamaService;

    @Autowired
    public LlamaController(LlamaService llamaService) {
        this.llamaService = llamaService;
    }

    @PostMapping("/generate")
    public String generateResponse(@RequestBody LlamaRequest request) {
        return llamaService.generateResponse(request.prompt());
    }



}
