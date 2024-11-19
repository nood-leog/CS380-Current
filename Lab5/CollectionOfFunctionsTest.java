import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectionOfFunctionsTest
{

    //5 methods - 5 test cases each


    /*
    Grade Assigner
    Takes in an integer as in input and returns a a letter grade as a character.
    */
    @Test
    void gradeAssigner()
    {
        CollectionOfFunctions test = new CollectionOfFunctions();
        assertEquals('F', test.gradeAssigner(59)); // 59 < 60
        assertEquals('D', test.gradeAssigner(69)); // 69 < 70
        assertEquals('C', test.gradeAssigner(79)); // 79 < 80
        assertEquals('B', test.gradeAssigner(89)); // 89 < 90
        assertEquals('A', test.gradeAssigner(90)); // 90
    }

    /*
     Sorting Algorithm
     Takes in a reference to an integer array as input and sorts the array.
     */

    @Test
    void sortArray() {
        CollectionOfFunctions test = new CollectionOfFunctions();

        int[] nums = {3, 2, 1};
        test.sortArray(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums); // 1, 2, 3

        int[] nums2 = {3, 1, 2};
        test.sortArray(nums2);
        assertArrayEquals(new int[]{1, 2, 3}, nums2); // 1, 2, 3

        int[] nums3 = {2, 3, 1};
        test.sortArray(nums3);
        assertArrayEquals(new int[]{1, 2, 3}, nums3); // 1, 2, 3

        int[] nums4 = {2, 1, 3};
        test.sortArray(nums4);
        assertArrayEquals(new int[]{1, 2, 3}, nums4); // 1, 2, 3

        int[] nums5 = {1, 3, 2};
        test.sortArray(nums5);
        assertArrayEquals(new int[]{1, 2, 3}, nums5); // 1, 2, 3
    }


    /*
    Row Summation
    Takes in a reference to a multidimensional array and integer x.
    Returns the summation of the row at position x in the 2-d array.
    */
    @Test
    void rowSum()
    {
        CollectionOfFunctions test = new CollectionOfFunctions();
        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}};

        assertEquals(6, test.rowSum(nums, 0)); // 1 + 2 + 3 = 6
        assertEquals(15, test.rowSum(nums, 1)); // 4 + 5 + 6 = 15
        assertEquals(24, test.rowSum(nums, 2)); // 7 + 8 + 9 = 24
        assertEquals(33, test.rowSum(nums, 3)); // 10 + 11 + 12 = 33
        assertEquals(42, test.rowSum(nums, 4)); // 13 + 14 + 15 = 42
    }


    /*
    Minimum Value
    Takes in a reference to an array and returns the minimum value value from the array.
    */
    @Test
    void minimumValue()
    {
        CollectionOfFunctions test = new CollectionOfFunctions();

        assertArrayEquals(new int[]{1, 0}, test.minimumValue(new int[]{1, 2, 3})); // 1
        assertArrayEquals(new int[]{1, 0}, test.minimumValue(new int[]{1, 3, 2})); // 1
        assertArrayEquals(new int[]{-1, 1}, test.minimumValue(new int[]{2, -1, 3})); // 1
        assertArrayEquals(new int[]{-1, 1}, test.minimumValue(new int[]{2, -1, 3, 4})); // -1
        assertArrayEquals(new int[]{-1, 2}, test.minimumValue(new int[]{2, 3, -1, 4})); // -1
    }


    /*
    String Cleaner
    Takes in as in input a String str and a char x.
    Returns a new string that removed all of the characters x from str.
     */
    @Test
    void stringCleaner() {
        CollectionOfFunctions test = new CollectionOfFunctions();
        assertEquals("abc", test.stringCleaner("abc", 'd'));
        assertEquals("abc", test.stringCleaner("abcd", 'd'));
        assertEquals("abc", test.stringCleaner("dadbdcd", 'd'));
        assertEquals("ac", test.stringCleaner("abc", 'b'));
        assertEquals("bc", test.stringCleaner("abc", 'a'));
    }
}