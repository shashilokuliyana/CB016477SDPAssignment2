package com.sdp.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InstructionRequest {

    @NotBlank(message = "Delivery instruction is required")
    @Size(min = 5, max = 500, message = "Delivery instruction must be between 5 and 500 characters")
    private String instructionText;

    public InstructionRequest() {
    }

    public String getInstructionText() {
        return instructionText;
    }

    public void setInstructionText(String instructionText) {
        this.instructionText = instructionText;
    }
}