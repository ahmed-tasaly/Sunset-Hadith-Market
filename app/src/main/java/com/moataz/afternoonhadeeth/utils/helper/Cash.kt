package com.moataz.afternoonhadeeth.ui.adapter

// Have the function ArrayChallenge(arr) read the array of numbers stored in arr which will contain a sliding window size, N, as the first element in the array and the rest will be a list of numbers. Your program should return the Moving Median for each element based on the element and its N-1 predecessors, where N is the sliding window size. The final output should be a string with the moving median corresponding to each entry in the original array separated by commas.
//
//Note that for the first few elements (until the window size is reached), the median is computed on a smaller number of entries. For example: if arr is [3, 1, 3, 5, 10, 6, 4, 3, 1] then your program should output "1,2,3,5,6,6,4,3"
//Examples
//Input: arrayOf(5, 2, 4, 6)
//Output: 2,3,4
//Input: arrayOf(3, 0, 0, -2, 0, 2, 0, -2)
//Output: 0,0,0,0,0,0,0




fun ArrayChallenge(arr: Array<Int>): Int {
    val n = arr[0]
    val list = arr.slice(1 until arr.size)
    val result = mutableListOf<Int>()
    for (i in list.indices) {
        val median = getMedian(list.slice(0 until i +1))
        result.add(median)
    }
    // add comma between the integers
    return result.joinToString(",").replace(",", "").toInt().shl(1)
}

fun getMedian(list: List<Int>): Int {
    val sortedList = list.sorted()
    val size = sortedList.size
    return if (size % 2 == 0) {
        val mid1 = sortedList[size / 2 - 1]
        val mid2 = sortedList[size / 2]
        (mid1 + mid2) / 2
    } else {
        sortedList[size / 2]
    }
}




// we will have more than 1000 objects in the collection and we will often add/remove objects in it (e.g. in the middle of the collection, not the first or last element). Which collections you will choose for this case?
//
//HashMap
//LinkedList
//ArrayList
//TreeMap
//TreeSet
//HashSet
//LinkedHashSet
//ArrayDeque









// You are given an array of integers numbers and two integers left and right. You task is to calculate a boolean array result, where result[i] = true if there exists an integer x, such that numbers[i] = (i + 1) * x and left ≤ x ≤ right. Otherwise, result[i] should be set to false.
//
//Example
//
//For numbers = [8, 5, 6, 16, 5], left = 1, and right = 3, the output should be solution(numbers, left, right) = [false, false, true, false, true].
//
//For numbers[0] = 8, we need to find a value of x such that 1 * x = 8, but the only value that would work is x = 8 which doesn't satisfy the boundaries 1 ≤ x ≤ 3, so result[0] = false.
//For numbers[1] = 5, we need to find a value of x such that 2 * x = 5, but there is no integer value that would satisfy this equation, so result[1] = false.
//For numbers[2] = 6, we can choose x = 2 because 3 * 2 = 6 and 1 ≤ 2 ≤ 3, so result[2] = true.
//For numbers[3] = 16, there is no an integer 1 ≤ x ≤ 3, such that 4 * x = 16, so result[3] = false.
//For numbers[4] = 5, we can choose x = 1 because 5 * 1 = 5 and 1 ≤ 1 ≤ 3, so result[4] = true.
//Input/Output
//
//[execution time limit] 3 seconds (kt)
//
//[input] array.integer numbers
//
//An array of integers.
//
//Guaranteed constraints:
//1 ≤ numbers.length ≤ 100,
//1 ≤ numbers[i] ≤ 106.
//
//[input] integer left
//
//An integer representing the lower bound for x.
//
//Guaranteed constraints:
//1 ≤ left ≤ 104.
//
//[input] integer right
//
//An integer representing the upper bound for x.
//
//Guaranteed constraints:
//1 ≤ left ≤ right ≤ 104.
//
//[output] array.boolean
//
//A boolean array result described above.


fun solution(numbers: Array<Int>, left: Int, right: Int): Array<Boolean> {
    val result = Array(numbers.size) { false }
    for (i in numbers.indices) {
        val x = (i + 1) * numbers[i]
        if (x in left..right) {
            result[i] = true
        }
    }
    return result
}

fun solution(numbers: MutableList<Int>, left: Int, right: Int): MutableList<Boolean> {
    val result = mutableListOf<Boolean>()
    for (i in numbers.indices) {
        val x = numbers[i] / (i+1)
        if(x.toString().contains(".") && x >= left && x <= right) result.add(true) else result.add(false)
    }
    return result
}

// Given a string s consisting of lowercase English letters, find the number of consecutive triplets within s formed by unique characters. In other words, count the number of indices i, such that s[i], s[i + 1], and s[i + 2] are all pairwise distinct.
//
//Example
//
//For s = "abcdaaae", the output should be solution(s) = 3.
//
//For i = 0, s[0] = 'a', s[1] = 'b', and s[2] = 'c', which are pairwise distinct;
//For i = 1, s[1] = 'b', s[2] = 'c', and s[3] = 'd', which are pairwise distinct;
//For i = 2, s[2] = 'c', s[3] = 'd', and s[4] = 'a', which are pairwise distinct;
//For i = 3, s[3] = 'd', s[4] = 'a', and s[5] = 'a', which are not pairwise distinct because s[4] and s[5] are equal;
//For i = 4, s[4] = 'a', s[5] = 'a', and s[6] = 'a', which are not pairwise distinct because any two of these characters are equal;
//For i = 5, s[5] = 'a', s[6] = 'a', and s[7] = 'e', which are not pairwise distinct because s[5] and s[6] are equal.
//For i = 6 or i = 7, at least one of characters s[i + 1] or s[i + 2] doesn't exist.
//For s = "abacaba", the output should be solution(s) = 2.
//
//There are only 2 indices meeting the criteria:
//
//i = 1: s[1] = 'b', s[2] = 'a', and s[3] = 'c';
//i = 3: s[3] = 'c', s[4] = 'a', and s[5] = 'b'.
//All other triplets will contain more than one a character.
//
//Input/Output
//
//[execution time limit] 3 seconds (kt)
//
//[input] string s
//
//A string consisting of lowercase English letters.
//
//Guaranteed constraints:
//1 ≤ s.length ≤ 1000.
//
//[output] integer
//
//The number of indices i in s, such that characters s[i], s[i + 1], and s[i + 2] all exist and are pairwise distinct.


fun solution(s: String): Int {
    var count = 0
    for (i in 0 until s.length - 2) {
        if (s[i] != s[i + 1] && s[i] != s[i + 2] && s[i + 1] != s[i + 2]) {
            count++
        }
    }
    return count
}


// Given a string s consisting of lowercase English letters, find the number of consecutive triplets within s formed by unique characters. In other words, count the number of indices i, such that s[i], s[i + 1], and s[i + 2] are all pairwise distinct.
//
//Example
//
//For s = "abcdaaae", the output should be solution(s) = 3.
//
//For i = 0, s[0] = 'a', s[1] = 'b', and s[2] = 'c', which are pairwise distinct;
//For i = 1, s[1] = 'b', s[2] = 'c', and s[3] = 'd', which are pairwise distinct;
//For i = 2, s[2] = 'c', s[3] = 'd', and s[4] = 'a', which are pairwise distinct;
//For i = 3, s[3] = 'd', s[4] = 'a', and s[5] = 'a', which are not pairwise distinct because s[4] and s[5] are equal;
//For i = 4, s[4] = 'a', s[5] = 'a', and s[6] = 'a', which are not pairwise distinct because any two of these characters are equal;
//For i = 5, s[5] = 'a', s[6] = 'a', and s[7] = 'e', which are not pairwise distinct because s[5] and s[6] are equal.
//For i = 6 or i = 7, at least one of characters s[i + 1] or s[i + 2] doesn't exist.
//For s = "abacaba", the output should be solution(s) = 2.
//
//There are only 2 indices meeting the criteria:
//
//i = 1: s[1] = 'b', s[2] = 'a', and s[3] = 'c';
//i = 3: s[3] = 'c', s[4] = 'a', and s[5] = 'b'.
//All other triplets will contain more than one a character.
//
//Input/Output
//
//[execution time limit] 3 seconds (kt)
//
//[input] string s
//
//A string consisting of lowercase English letters.
//
//Guaranteed constraints:
//1 ≤ s.length ≤ 1000.
//
//[output] integer
//
//The number of indices i in s, such that characters s[i], s[i + 1], and s[i + 2] all exist and are pairwise distinct.


fun solutionp(s: String): Int {
    var count = 0
    for (i in 0 until s.length - 2) {
        if (s[i] != s[i + 1] && s[i] != s[i + 2] && s[i + 1] != s[i + 2]) {
            count++
        }
    }
    return count
}


// Imagine you are given a number line represented by [0, length], and you are coloring different coordinates along the line with different colors. Specifically, you are given a bunch of queries that determines how to color the coordinates along the line, in the following format:
//
//[coord, color] - color the coordinate coord with color color. The previous color is overwritten if there was any.
//After processing each query, record the current number of unique colors along the number line. Your task is to return an array of length queries.length containing these records after processing all queries.
//
//Example
//
//For length = 8 and queries = [[1, 2], [3, 5], [3, 1], [3, 2], [4, 5], [6, 1]], the output should be solution(length, queries) = [1, 2, 2, 1, 2, 3].
//
//Expand to see the example video.
//1.00
//
//Let's consider all queries:
//
//[1, 2] - color the coordinate 1 with color 2. After this query, the number line contains the following unique colors [2], so record 1.
//[3, 5] - color the coordinate 3 with color 5. After this query, the number line contains the following unique colors [2, 5], so record 2.
//[3, 1] - color the coordinate 3 with color 1. After this query, the number line contains the following unique colors [1, 2], so record 2.
//[3, 2] - color the coordinate 3 with color 2. After this query, the number line contains the following unique colors [2], so record 1.
//[4, 5] - color the coordinate 4 with color 5. After this query, the number line contains the following unique colors [2, 5], so record 2.
//[6, 1] - color the coordinate 6 with color 1. After this query, the number line contains the following unique colors [1, 2, 5], so record 3.
//After processing all queries, the final output is [1, 2, 2, 1, 2, 3].
//Input/Output
//
//[execution time limit] 3 seconds (kt)
//
//[input] integer length
//
//An integer representing the rightmost endpoint of the number line.
//
//Guaranteed constraints:
//1 ≤ length ≤ 109.
//
//[input] array.array.integer queries
//
//An array of queries.
//
//Guaranteed constraints:
//1 ≤ queries.length ≤ 105,
//0 ≤ queries[i][0] ≤ n,
//1 ≤ queries[i][1] ≤ 109.
//
//[output] array.integer
//
//Return an array of length queries.length, such that the ith element contains the number of unique colors after the ith query is processed.


fun solution(length: Int, queries: Array<Array<Int>>): IntArray {
    val colors = IntArray(length)
    for (query in queries) {
        colors[query[0] - 1] = query[1]
    }
    val result = IntArray(queries.size)
    var count = 0
    var prev = colors[0]
    for (i in 1 until length) {
        if (colors[i] != prev) {
            count++
        }
        prev = colors[i]
        result[i - 1] = count
    }
    return result
}


// You are given an array schedules representing existing meetings for all employees over the course of an entire day, and an integer length representing the length of a new meeting in minutes.
//
//Each element in schedules is an array, such that schedules[i][j] represents the jth meeting for the ith employee. Each meeting is represented by a pair of integers: [startTime, finishTime], where each integer represents the number of minutes passed since the beginning of the day. startTime and finishTime do not exceed 24 × 60.
//
//Your task is to find the earliest possible time when a meeting of length length can be scheduled for all employees. If there is no time block which suits everyone, return -1.
//
//Note: The new meeting should also fit within the same day, so the finish time for this meeting should not exceed 24 × 60.
//
//Example
//
//For
//schedules = [
//  [[60, 150], [180, 240]],
//  [[0, 210], [360, 420]]
//]
//and length = 120, the output should be solution(schedules, length) = 240.
//
//Expand to see the example video.
//1.00
//
//Explanation:
//If the new meeting is scheduled to start from minute 240, it will last until minute 360. The interval [240, 360] does not intersect with any other intervals from schedules, so it's possible to schedule this meeting to allow all employees to attend.
//A meeting with a duration of 120 minutes that suits the schedule of all employees can't be scheduled earlier, so the answer is 240.
//
//For
//schedules = [
//  [[480, 510]],
//  [[240, 330]],
//  [[375, 400]]
//]
//and length = 180, the output should be solution(schedules, length) = 0.
//
//Explanation:
//If the new meeting is scheduled right at the beginning of the day, then it wouldn't intersect with any other meetings, so the answer is 0.
//
//For
//schedules = [
//  [[0, 1439]],
//  [[0, 1439]],
//  [[0, 390], [480, 510]]
//]
//and length = 90, the output should be solution(schedules, length) = -1.
//Explanation:
//The first two employees are booked for the whole day, so it's not possible to have a meeting with a duration of 90 minutes that all the employees can attend. Thus the answer is -1.
//
//Input/Output
//
//[execution time limit] 3 seconds (kt)
//
//[input] array.array.array.integer schedules
//
//An array of arrays of integer arrays, where each integer array schedules[i][j] contains 2 distinct integers representing the jth meeting booked for the ith employee. Each integer within the array represents the time (in minutes) passed since the beginning of the day.
//
//Guaranteed constraints:
//1 ≤ schedules.length ≤ 100,
//0 ≤ schedules[i].length ≤ 100,
//schedules[i][j].length = 2,
//0 ≤ schedules[i][j][0] < schedules[i][j][1] ≤ 1440.
//
//[input] integer length
//
//An integer containing length of the new meeting in minutes.
//
//Guaranteed constraints:
//1 ≤ length ≤ 24 * 60.
//
//[output] integer
//
//The earliest possible time when the meeting can be scheduled that would fit the schedules of all employees. The output should be represented as minutes passed since the beginning of the day.









// The city's underground subway stations are split into zones A, B, and C, due to differences in pricing, as shown below:
//
//Underground zones
//
//There are 3 types of tickets, sorted by their price in ascending order:
//
//AB for travel in and between zones A and B only;
//BC for travel in and between zones B and C only;
//ABC for travel in and between any zones - A and B and C.
//You are given 3 arrays of strings stationsA, stationsB, and stationsC, which contain the names of stations in that particular zone. You are also given 2 strings origin and destination, which contain names of the starting and ending station for a trip. Your task is to return a string with the name of the cheapest ticket that will allow you to get from the origin station to destination station. If the origin station or the destination station doesn't exist in any of the zones, return an empty string.
//
//Note: You can assume that a direct route always exists. E.g., if you need to go from zone A to zone B, you shouldn't go via zone C.
//
//Example
//
//For stationsA = ["Green Park", "Holborn"], stationsB = ["Mile End", "Bow Road"], stationsC = ["Forest Hill", "Balham"], origin = "Forest Hill", and destination = "Green Park", the output should be solution(stationsA, stationsB, stationsC, origin, destination) = "ABC".
//
//Explanation: "Forest Hill" is to zone C and "Green Park" is in zone A, so only the ABC ticket would allow you to travel between them.
//
//For stationsA = ["Green Park", "Holborn"], stationsB = ["Mile End", "Bow Road"], stationsC = ["Forest Hill", "Balham"], origin = "Holborn", and destination = "Green Park", the output should be solution(stationsA, stationsB, stationsC, origin, destination) = "AB".
//
//Explanation: Both "Holborn" and "Green Park" are in zone A, so you can potentially use either the "AB" or the "ABC" ticket. Given that the "AB" ticket is cheaper, the answer is "AB".
//
//For stationsA = ["Green Park"], stationsB = ["Mile End"], stationsC = ["Forest Hill"], origin = "Holborn", and destination = "Forest Hill", the output should be solution(stationsA, stationsB, stationsC, origin, destination) = "".
//
//Explanation: "Holborn" is not present in any of the zones, so the answer is "".
//
//Input/Output
//
//[execution time limit] 3 seconds (kt)
//
//[input] array.string stationsA
//
//An array of strings containing names of subway stations.
//
//Guaranteed constraints:
//1 ≤ stationsA.length ≤ 1000,
//1 ≤ stationsA[i].length ≤ 50.
//
//[input] array.string stationsB
//
//An array of strings containing names of subway stations.
//
//Guaranteed constraints:
//1 ≤ stationsB.length ≤ 1000,
//1 ≤ stationsB[i].length ≤ 50.
//
//[input] array.string stationsC
//
//An array of strings containing names of subway stations.
//
//Guaranteed constraints:
//1 ≤ stationsC.length ≤ 1000,
//1 ≤ stationsC[i].length ≤ 50.
//
//[input] string origin
//
//A non-empty string which contains the name of the origin subway station.
//
//Guaranteed constraints:
//1 ≤ origin.length ≤ 50.
//
//[input] string destination
//
//A non-empty string which contains the name of the destination subway station.
//
//Guaranteed constraints:
//1 ≤ destination.length ≤ 50.
//
//[output] string
//
//Return the name of the cheapest ticket which allows you to travel between the origin station the destination station, either: "AB", "BC" or "ABC". If either the origin or the destination station doesn't exist in any of the zones, return and empty string "".










