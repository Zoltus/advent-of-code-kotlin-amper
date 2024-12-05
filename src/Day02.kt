import kotlin.math.abs

fun main() {
    val input: List<String> = readInput("Day02")
    val reports: List<List<Int>> = input.map { row -> row.split(" ").map { it.toInt() } }
    //Part1
    val safeReportCount = reports.count(::isSafe)
    println("Safe report count $safeReportCount")
    //Part2
    val safeReportCount2: Int = reports.count { report ->
        // if safe dont force
        if (isSafe(report)) {
            true
        } else {
            //Force check with 1 tolerance
            for (i in 0..<report.size) {
                val mutList = report.toMutableList()
                mutList.removeAt(i)
                if (isSafe(mutList)) {
                    return@count true
                }
            }
            false
        }
    }
    println("Safe report count2 $safeReportCount2")

}

fun isSafe(levels: List<Int>): Boolean {
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
    return isSafe
}