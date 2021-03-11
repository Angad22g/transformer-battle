package com.aequilibrium.transformers.service;


import com.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

import java.util.List;

public interface TransformerBattleService {

    public TransformersBattleResponseDTO battleWinners(List<Long> transformersIds);

}
