package com.vkolesnyk.studying.sender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EthereumBlock {
    private Long height;
    private String time;
}
