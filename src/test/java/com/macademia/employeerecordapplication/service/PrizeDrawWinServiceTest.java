package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.repository.PrizeDrawWinRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class PrizeDrawWinServiceTest {

    @Mock
    private PrizeDrawWinRepository prizeDrawWinRepository;

    @InjectMocks
    private PrizeDrawWinService prizeDrawWinService;

    @Test
    public void findWinnerForThisMonth_func_should_throw_exception_when_there_is_no_prize_draw_win() {
        //given
        BDDMockito
                .given(prizeDrawWinRepository.findTopByOrderByDateDesc())
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> prizeDrawWinService.findWinnerForThisMonth());

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Prize winner is not calculated for this month yet.");
    }
}