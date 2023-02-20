package de.kayteem.apps.tfmgamelogconverter.model.internal

import TestDataFactory
import org.junit.Assert
import org.junit.Test

/**
 * Test Suite for Corporation
 *
 * Author: Tobias Mielke
 */
class ACorporation {

    // test cases - play counts
    @Test
    fun canCalculatePlayedOnMap() {

        // execution
        val played = CORPS_LIST.map { it.playedOnMap() }

        // post-condition
        val expectationCorp1 = mapOf(
            Boards.THARSIS to 1,
            Boards.ELYSIUM to 1,
        )
        val expectationCorp2 = mapOf(
            Boards.THARSIS to 1
        )
        val expectationCorp3 = mapOf(
            Boards.ELYSIUM to 1
        )
        val expectationCorp4 = mapOf(
            Boards.ELYSIUM to 1
        )
        val expectationCorp5 = mapOf(
            Boards.THARSIS to 1
        )
        val expectationCorp6 = mapOf(
            Boards.THARSIS to 1
        )

        Assert.assertEquals(expectationCorp1, played[0])
        Assert.assertEquals(expectationCorp2, played[1])
        Assert.assertEquals(expectationCorp3, played[2])
        Assert.assertEquals(expectationCorp4, played[3])
        Assert.assertEquals(expectationCorp5, played[4])
        Assert.assertEquals(expectationCorp6, played[5])
    }

    @Test
    fun canCalculatePlayedByYou() {

        // execution
        val played = CORPS_LIST.map { it.playedByYou() }

        // post-condition
        Assert.assertEquals(1, played[0])
        Assert.assertEquals(0, played[1])
        Assert.assertEquals(0, played[2])
        Assert.assertEquals(1, played[3])
        Assert.assertEquals(1, played[4])
        Assert.assertEquals(0, played[5])
    }

    @Test
    fun canCalculatePlayedByOpponents() {

        // execution
        val played = CORPS_LIST.map { it.playedByOpponents() }

        // post-condition
        Assert.assertEquals(1, played[0])
        Assert.assertEquals(1, played[1])
        Assert.assertEquals(1, played[2])
        Assert.assertEquals(0, played[3])
        Assert.assertEquals(0, played[4])
        Assert.assertEquals(1, played[5])
    }

    @Test
    fun canCalculatePlayedTotal() {

        // execution
        val played = CORPS_LIST.map { it.playedTotal() }

        // post-condition
        Assert.assertEquals(2, played[0])
        Assert.assertEquals(1, played[1])
        Assert.assertEquals(1, played[2])
        Assert.assertEquals(1, played[3])
        Assert.assertEquals(1, played[4])
        Assert.assertEquals(1, played[5])
    }

    
    // test cases - win counts
    @Test
    fun canCalculateWonOnMap() {

        // execution
        val won = CORPS_LIST.map { it.wonOnMap() }

        // post-condition
        val expectationCorp1 = mapOf<Boards, Int>(
            Boards.THARSIS to 1
        )
        val expectationCorp2 = mapOf<Boards, Int>(

        )
        val expectationCorp3 = mapOf<Boards, Int>(
            Boards.ELYSIUM to 1
        )
        val expectationCorp4 = mapOf<Boards, Int>(

        )
        val expectationCorp5 = mapOf<Boards, Int>(
            Boards.THARSIS to 1
        )
        val expectationCorp6 = mapOf<Boards, Int>(

        )

        Assert.assertEquals(expectationCorp1, won[0])
        Assert.assertEquals(expectationCorp2, won[1])
        Assert.assertEquals(expectationCorp3, won[2])
        Assert.assertEquals(expectationCorp4, won[3])
        Assert.assertEquals(expectationCorp5, won[4])
        Assert.assertEquals(expectationCorp6, won[5])
    }
    
    @Test
    fun canCalculateWonByYou() {

        // execution
        val won = CORPS_LIST.map { it.wonByYou() }

        // post-condition
        Assert.assertEquals(1, won[0])
        Assert.assertEquals(0, won[1])
        Assert.assertEquals(0, won[2])
        Assert.assertEquals(0, won[3])
        Assert.assertEquals(1, won[4])
        Assert.assertEquals(0, won[5])
    }
    
    @Test
    fun canCalculateWonByOpponents() {

        // execution
        val won = CORPS_LIST.map { it.wonByOpponents() }

        // post-condition
        Assert.assertEquals(0, won[0])
        Assert.assertEquals(0, won[1])
        Assert.assertEquals(1, won[2])
        Assert.assertEquals(0, won[3])
        Assert.assertEquals(0, won[4])
        Assert.assertEquals(0, won[5])
    }
    
    @Test
    fun canCalculateWonTotal() {

        // execution
        val won = CORPS_LIST.map { it.wonTotal() }

        // post-condition
        Assert.assertEquals(1, won[0])
        Assert.assertEquals(0, won[1])
        Assert.assertEquals(1, won[2])
        Assert.assertEquals(0, won[3])
        Assert.assertEquals(1, won[4])
        Assert.assertEquals(0, won[5])
    }


    // test cases - win rates
    @Test
    fun canCalculateWinRateOnMapByYou() {

        // execution
        val winRate = CORPS_LIST.map { it.winRateOnMapByYou() }

        // post-condition
        val expectationCorp1 = mapOf<Boards, Double?>(
            Boards.THARSIS to 1.0
        )
        val expectationCorp2 = mapOf<Boards, Double?>(

        )
        val expectationCorp3 = mapOf<Boards, Double?>(

        )
        val expectationCorp4 = mapOf<Boards, Double?>(
            Boards.ELYSIUM to 0.0
        )
        val expectationCorp5 = mapOf<Boards, Double?>(
            Boards.THARSIS to 1.0
        )
        val expectationCorp6 = mapOf<Boards, Double?>(

        )

        Assert.assertEquals(expectationCorp1, winRate[0])
        Assert.assertEquals(expectationCorp2, winRate[1])
        Assert.assertEquals(expectationCorp3, winRate[2])
        Assert.assertEquals(expectationCorp4, winRate[3])
        Assert.assertEquals(expectationCorp5, winRate[4])
        Assert.assertEquals(expectationCorp6, winRate[5])
    }

    @Test
    fun canCalculateWinRateOnMapByOpponents() {

        // execution
        val winRate = CORPS_LIST.map { it.winRateOnMapByOpponents() }

        // post-condition
        val expectationCorp1 = mapOf<Boards, Double?>(
            Boards.ELYSIUM to 0.0
        )
        val expectationCorp2 = mapOf<Boards, Double?>(
            Boards.THARSIS to 0.0
        )
        val expectationCorp3 = mapOf<Boards, Double?>(
            Boards.ELYSIUM to 1.0
        )
        val expectationCorp4 = mapOf<Boards, Double?>(

        )
        val expectationCorp5 = mapOf<Boards, Double?>(

        )
        val expectationCorp6 = mapOf<Boards, Double?>(
            Boards.THARSIS to 0.0
        )

        Assert.assertEquals(expectationCorp1, winRate[0])
        Assert.assertEquals(expectationCorp2, winRate[1])
        Assert.assertEquals(expectationCorp3, winRate[2])
        Assert.assertEquals(expectationCorp4, winRate[3])
        Assert.assertEquals(expectationCorp5, winRate[4])
        Assert.assertEquals(expectationCorp6, winRate[5])
    }

    @Test
    fun canCalculateWinRateOnMap() {

        // execution
        val winRate = CORPS_LIST.map { it.winRateOnMap() }

        // post-condition
        val expectationCorp1 = mapOf<Boards, Double?>(
            Boards.THARSIS to 1.0,
            Boards.ELYSIUM to 0.0
        )
        val expectationCorp2 = mapOf<Boards, Double?>(
            Boards.THARSIS to 0.0,
        )
        val expectationCorp3 = mapOf<Boards, Double?>(
            Boards.ELYSIUM to 1.0

        )
        val expectationCorp4 = mapOf<Boards, Double?>(
            Boards.ELYSIUM to 0.0
        )
        val expectationCorp5 = mapOf<Boards, Double?>(
            Boards.THARSIS to 1.0
        )
        val expectationCorp6 = mapOf<Boards, Double?>(
            Boards.THARSIS to 0.0
        )

        Assert.assertEquals(expectationCorp1, winRate[0])
        Assert.assertEquals(expectationCorp2, winRate[1])
        Assert.assertEquals(expectationCorp3, winRate[2])
        Assert.assertEquals(expectationCorp4, winRate[3])
        Assert.assertEquals(expectationCorp5, winRate[4])
        Assert.assertEquals(expectationCorp6, winRate[5])
    }

    @Test
    fun canCalculateWinRateByYou() {

        // execution
        val winRate = CORPS_LIST.map { it.winRateByYou() }

        // post-condition
        Assert.assertEquals(1.0, winRate[0]!!, delta)
        Assert.assertNull(winRate[1])
        Assert.assertNull(winRate[2])
        Assert.assertEquals(0.0, winRate[3]!!, delta)
        Assert.assertEquals(1.0, winRate[4]!!, delta)
        Assert.assertNull(winRate[5])
    }

    @Test
    fun canCalculateWinRateByOpponents() {

        // execution
        val winRate = CORPS_LIST.map { it.winRateByOpponents() }

        // post-condition
        Assert.assertEquals(0.0, winRate[0]!!, delta)
        Assert.assertEquals(0.0, winRate[1]!!, delta)
        Assert.assertEquals(1.0, winRate[2]!!, delta)
        Assert.assertNull(winRate[3])
        Assert.assertNull(winRate[4])
        Assert.assertEquals(0.0, winRate[5]!!, delta)
    }

    @Test
    fun canCalculateWinRateTotal() {

        // execution
        val winRate = CORPS_LIST.map { it.winRateTotal() }

        // post-condition
        Assert.assertEquals(0.5, winRate[0]!!, delta)
        Assert.assertEquals(0.0, winRate[1]!!, delta)
        Assert.assertEquals(1.0, winRate[2]!!, delta)
        Assert.assertEquals(0.0, winRate[3]!!, delta)
        Assert.assertEquals(1.0, winRate[4]!!, delta)
        Assert.assertEquals(0.0, winRate[5]!!, delta)
    }

    
    // companion
    companion object {

        const val delta = 0.001

        private val CORP_1 = TestDataFactory.buildCorp1()
        private val CORP_2 = TestDataFactory.buildCorp2()
        private val CORP_3 = TestDataFactory.buildCorp3()
        private val CORP_4 = TestDataFactory.buildCorp4()
        private val CORP_5 = TestDataFactory.buildCorp5()
        private val CORP_6 = TestDataFactory.buildCorp6()
        
        val CORPS_LIST = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

    }

}