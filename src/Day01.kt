import kotlin.math.abs

fun main() {
    val input = readInput("Day01")

    //Part1
    val pairs: List<Pair<Int, Int>> = input.map { line ->
        val left = line.substringBefore(" ")
        val right = line.substringAfterLast(" ")
        left.toInt() to right.toInt()
    }

    val left = pairs.map { it.first }.sorted()
    val right = pairs.map { it.second }.sorted()

    val distances: List<Int> = left.mapIndexed { i, leftVal ->
        val matching = right[i]
        val distance = abs(leftVal - matching)
        return@mapIndexed distance
    }

    val sumDistances = distances.sum()
    println("Sum $sumDistances")

    //Part 2
    val similarityScore = left.sumOf { lNum ->
        val count = right.count { rNum -> rNum == lNum }
        lNum * count
    }
    println("SimilarityScore $similarityScore")
}