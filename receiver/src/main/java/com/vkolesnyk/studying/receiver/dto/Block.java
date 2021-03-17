package com.vkolesnyk.studying.receiver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    private Long blockNumber;
    private Long blockTime;
}
