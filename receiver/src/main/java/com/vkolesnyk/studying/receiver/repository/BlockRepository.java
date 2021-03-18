package com.vkolesnyk.studying.receiver.repository;

import com.vkolesnyk.studying.receiver.dto.Block;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlockRepository {

    private final JdbcTemplate jdbcTemplate;

    public BlockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean insertBlock(Block block) {
        var sql = "insert into block(blocknumber, blocktime) values(?, ?) on conflict(blocknumber) do nothing";
        return jdbcTemplate.update(sql, block.getBlockNumber(), block.getBlockTime()) != 0;
    }

}
