import kotlin.math.abs

fun main() {
    val input: List<String> = readInput("Day02")
    //Part1
    val safeReportCount: Int = input.count { report ->
        val levels = report.split(" ").map { it.toInt() }
        // Gets each possible pair next to each other
        val zipWithNext: List<Pair<Int, Int>> = levels.zipWithNext()
        //Check if it should be increasing/degreasing
        val increasing = zipWithNext[0].first < zipWithNext[0].second
        val isSafe: Boolean = zipWithNext.all { (left, right) ->
            // Get dif
            val dif = abs(left - right)
            val isUnsafeDif = dif > 3 || dif < 1
            if (increasing) {
                left < right && !isUnsafeDif
            } else {
                right < left && !isUnsafeDif
            }
        }
        isSafe
    }
    println("Safe report count $safeReportCount")

    //Part2
}

/*
Line = report
num = level
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
 */