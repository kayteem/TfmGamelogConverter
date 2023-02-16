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

        // setup
        val corps = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

        // execution
        val played = corps.map { it.playedOnMap() }

        // post-condition
        Assert.assertEquals(EXPECTED_PLAYED_ON_MAP_CORP_1, played[0])
        Assert.assertEquals(EXPECTED_PLAYED_ON_MAP_CORP_2, played[1])
        Assert.assertEquals(EXPECTED_PLAYED_ON_MAP_CORP_3, played[2])
        Assert.assertEquals(EXPECTED_PLAYED_ON_MAP_CORP_4, played[3])
        Assert.assertEquals(EXPECTED_PLAYED_ON_MAP_CORP_5, played[4])
        Assert.assertEquals(EXPECTED_PLAYED_ON_MAP_CORP_6, played[5])
    }

    @Test
    fun canCalculatePlayedByYou() {

        // setup
        val corps = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

        // execution
        val played = corps.map { it.playedByYou() }

        // post-condition
        Assert.assertEquals(EXPECTED_PLAYED_BY_YOU_CORP_1, played[0])
        Assert.assertEquals(EXPECTED_PLAYED_BY_YOU_CORP_2, played[1])
        Assert.assertEquals(EXPECTED_PLAYED_BY_YOU_CORP_3, played[2])
        Assert.assertEquals(EXPECTED_PLAYED_BY_YOU_CORP_4, played[3])
        Assert.assertEquals(EXPECTED_PLAYED_BY_YOU_CORP_5, played[4])
        Assert.assertEquals(EXPECTED_PLAYED_BY_YOU_CORP_6, played[5])
    }

    @Test
    fun canCalculatePlayedByOpponents() {

        // setup
        val corps = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

        // execution
        val played = corps.map { it.playedByOpponents() }

        // post-condition
        Assert.assertEquals(EXPECTED_PLAYED_BY_OPPONENTS_CORP_1, played[0])
        Assert.assertEquals(EXPECTED_PLAYED_BY_OPPONENTS_CORP_2, played[1])
        Assert.assertEquals(EXPECTED_PLAYED_BY_OPPONENTS_CORP_3, played[2])
        Assert.assertEquals(EXPECTED_PLAYED_BY_OPPONENTS_CORP_4, played[3])
        Assert.assertEquals(EXPECTED_PLAYED_BY_OPPONENTS_CORP_5, played[4])
        Assert.assertEquals(EXPECTED_PLAYED_BY_OPPONENTS_CORP_6, played[5])
    }

    @Test
    fun canCalculatePlayedTotal() {

        // setup
        val corps = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

        // execution
        val played = corps.map { it.playedTotal() }

        // post-condition
        Assert.assertEquals(EXPECTED_PLAYED_TOTAL_CORP_1, played[0])
        Assert.assertEquals(EXPECTED_PLAYED_TOTAL_CORP_2, played[1])
        Assert.assertEquals(EXPECTED_PLAYED_TOTAL_CORP_3, played[2])
        Assert.assertEquals(EXPECTED_PLAYED_TOTAL_CORP_4, played[3])
        Assert.assertEquals(EXPECTED_PLAYED_TOTAL_CORP_5, played[4])
        Assert.assertEquals(EXPECTED_PLAYED_TOTAL_CORP_6, played[5])
    }

    
    // test cases - win counts
    @Test
    fun canCalculateWonOnMap() {

        // setup
        val corps = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

        // execution
        val won = corps.map { it.wonOnMap() }

        // post-condition
        Assert.assertEquals(EXPECTED_WON_ON_MAP_CORP_1, won[0])
        Assert.assertEquals(EXPECTED_WON_ON_MAP_CORP_2, won[1])
        Assert.assertEquals(EXPECTED_WON_ON_MAP_CORP_3, won[2])
        Assert.assertEquals(EXPECTED_WON_ON_MAP_CORP_4, won[3])
        Assert.assertEquals(EXPECTED_WON_ON_MAP_CORP_5, won[4])
        Assert.assertEquals(EXPECTED_WON_ON_MAP_CORP_6, won[5])
    }
    
    @Test
    fun canCalculateWonByYou() {

        // setup
        val corps = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

        // execution
        val won = corps.map { it.wonByYou() }

        // post-condition
        Assert.assertEquals(EXPECTED_WON_BY_YOU_CORP_1, won[0])
        Assert.assertEquals(EXPECTED_WON_BY_YOU_CORP_2, won[1])
        Assert.assertEquals(EXPECTED_WON_BY_YOU_CORP_3, won[2])
        Assert.assertEquals(EXPECTED_WON_BY_YOU_CORP_4, won[3])
        Assert.assertEquals(EXPECTED_WON_BY_YOU_CORP_5, won[4])
        Assert.assertEquals(EXPECTED_WON_BY_YOU_CORP_6, won[5])
    }
    
    @Test
    fun canCalculateWonByOpponents() {

        // setup
        val corps = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

        // execution
        val won = corps.map { it.wonByOpponents() }

        // post-condition
        Assert.assertEquals(EXPECTED_WON_BY_OPPONENTS_CORP_1, won[0])
        Assert.assertEquals(EXPECTED_WON_BY_OPPONENTS_CORP_2, won[1])
        Assert.assertEquals(EXPECTED_WON_BY_OPPONENTS_CORP_3, won[2])
        Assert.assertEquals(EXPECTED_WON_BY_OPPONENTS_CORP_4, won[3])
        Assert.assertEquals(EXPECTED_WON_BY_OPPONENTS_CORP_5, won[4])
        Assert.assertEquals(EXPECTED_WON_BY_OPPONENTS_CORP_6, won[5])
    }
    
    @Test
    fun canCalculateWonTotal() {

        // setup
        val corps = listOf(CORP_1, CORP_2, CORP_3, CORP_4, CORP_5, CORP_6)

        // execution
        val won = corps.map { it.wonTotal() }

        // post-condition
        Assert.assertEquals(EXPECTED_WON_TOTAL_CORP_1, won[0])
        Assert.assertEquals(EXPECTED_WON_TOTAL_CORP_2, won[1])
        Assert.assertEquals(EXPECTED_WON_TOTAL_CORP_3, won[2])
        Assert.assertEquals(EXPECTED_WON_TOTAL_CORP_4, won[3])
        Assert.assertEquals(EXPECTED_WON_TOTAL_CORP_5, won[4])
        Assert.assertEquals(EXPECTED_WON_TOTAL_CORP_6, won[5])
    }

    
    // companion
    companion object {

        // corporations
        val CORP_1 = TestDataFactory.buildCorp1()
        val CORP_2 = TestDataFactory.buildCorp2()
        val CORP_3 = TestDataFactory.buildCorp3()
        val CORP_4 = TestDataFactory.buildCorp4()
        val CORP_5 = TestDataFactory.buildCorp5()
        val CORP_6 = TestDataFactory.buildCorp6()

        // expectations - played on map
        val EXPECTED_PLAYED_ON_MAP_CORP_1 = mapOf(
            Boards.THARSIS to 1,
            Boards.ELYSIUM to 1
        )
        val EXPECTED_PLAYED_ON_MAP_CORP_2 = mapOf(
            Boards.THARSIS to 1
        )
        val EXPECTED_PLAYED_ON_MAP_CORP_3 = mapOf(
            Boards.ELYSIUM to 1
        )
        val EXPECTED_PLAYED_ON_MAP_CORP_4 = mapOf(
            Boards.ELYSIUM to 1
        )
        val EXPECTED_PLAYED_ON_MAP_CORP_5 = mapOf(
            Boards.THARSIS to 1
        )
        val EXPECTED_PLAYED_ON_MAP_CORP_6 = mapOf(
            Boards.THARSIS to 1
        )

        // expectations - played by you
        const val EXPECTED_PLAYED_BY_YOU_CORP_1 = 1
        const val EXPECTED_PLAYED_BY_YOU_CORP_2 = 0
        const val EXPECTED_PLAYED_BY_YOU_CORP_3 = 0
        const val EXPECTED_PLAYED_BY_YOU_CORP_4 = 1
        const val EXPECTED_PLAYED_BY_YOU_CORP_5 = 1
        const val EXPECTED_PLAYED_BY_YOU_CORP_6 = 0

        // expectations - played by opponents
        const val EXPECTED_PLAYED_BY_OPPONENTS_CORP_1 = 1
        const val EXPECTED_PLAYED_BY_OPPONENTS_CORP_2 = 1
        const val EXPECTED_PLAYED_BY_OPPONENTS_CORP_3 = 1
        const val EXPECTED_PLAYED_BY_OPPONENTS_CORP_4 = 0
        const val EXPECTED_PLAYED_BY_OPPONENTS_CORP_5 = 0
        const val EXPECTED_PLAYED_BY_OPPONENTS_CORP_6 = 1

        // expectations - played total
        const val EXPECTED_PLAYED_TOTAL_CORP_1 = 2
        const val EXPECTED_PLAYED_TOTAL_CORP_2 = 1
        const val EXPECTED_PLAYED_TOTAL_CORP_3 = 1
        const val EXPECTED_PLAYED_TOTAL_CORP_4 = 1
        const val EXPECTED_PLAYED_TOTAL_CORP_5 = 1
        const val EXPECTED_PLAYED_TOTAL_CORP_6 = 1

        // expectations - won on map
        val EXPECTED_WON_ON_MAP_CORP_1 = mapOf(
            Boards.THARSIS to 1
        )
        val EXPECTED_WON_ON_MAP_CORP_2 = mapOf<Boards, Int>(
            
        )
        val EXPECTED_WON_ON_MAP_CORP_3 = mapOf(
            Boards.ELYSIUM to 1
        )
        val EXPECTED_WON_ON_MAP_CORP_4 = mapOf<Boards, Int>(

        )
        val EXPECTED_WON_ON_MAP_CORP_5 = mapOf(
            Boards.THARSIS to 1
        )
        val EXPECTED_WON_ON_MAP_CORP_6 = mapOf<Boards, Int>(
            
        )

        // expectations - won by you
        const val EXPECTED_WON_BY_YOU_CORP_1 = 1
        const val EXPECTED_WON_BY_YOU_CORP_2 = 0
        const val EXPECTED_WON_BY_YOU_CORP_3 = 0
        const val EXPECTED_WON_BY_YOU_CORP_4 = 0
        const val EXPECTED_WON_BY_YOU_CORP_5 = 1
        const val EXPECTED_WON_BY_YOU_CORP_6 = 0

        // expectations - won by opponents
        const val EXPECTED_WON_BY_OPPONENTS_CORP_1 = 0
        const val EXPECTED_WON_BY_OPPONENTS_CORP_2 = 0
        const val EXPECTED_WON_BY_OPPONENTS_CORP_3 = 1
        const val EXPECTED_WON_BY_OPPONENTS_CORP_4 = 0
        const val EXPECTED_WON_BY_OPPONENTS_CORP_5 = 0
        const val EXPECTED_WON_BY_OPPONENTS_CORP_6 = 0

        // expectations - won total
        const val EXPECTED_WON_TOTAL_CORP_1 = 1
        const val EXPECTED_WON_TOTAL_CORP_2 = 0
        const val EXPECTED_WON_TOTAL_CORP_3 = 1
        const val EXPECTED_WON_TOTAL_CORP_4 = 0
        const val EXPECTED_WON_TOTAL_CORP_5 = 1
        const val EXPECTED_WON_TOTAL_CORP_6 = 0
    }

}