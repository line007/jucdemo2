package com.line.datastructs.array;

/**
 * @desc 稀疏数组
 * @Author xw
 * @Date 2019/8/10
 */
public class SparseArrayDemo {
    public static void main(String[] args) {
        int[][] twoDimensionalArray = new int[6][7];
        twoDimensionalArray[0][3] = 22;
        twoDimensionalArray[0][6] = 15;
        twoDimensionalArray[1][1] = 11;
        twoDimensionalArray[1][5] = 17;
        twoDimensionalArray[2][3] = -6;
        twoDimensionalArray[3][5] = 39;
        twoDimensionalArray[4][0] = 91;
        twoDimensionalArray[5][2] = 28;
        printArray(twoDimensionalArray);

        // 二维数组转稀疏数组
        int[][] sparseArray = twoDimensionalArrayToSparseArray(twoDimensionalArray);
        System.out.println("稀疏数组==========================");
        printArray(sparseArray);

        // 稀疏数组转二维数组
        int[][] twoDimensionalArrayNew = sparseArrayToTowDimensionalArray(sparseArray);
        System.out.println("转之后的数组==========================");
        printArray(twoDimensionalArrayNew);
    }

    private static int[][] sparseArrayToTowDimensionalArray(int[][] sparseArray) {
        int[][] twoDimensionalArrayNew = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray[0][2]; i++) { // 从第二行开始遍历
            twoDimensionalArrayNew[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return twoDimensionalArrayNew;
    }

    private static int[][] twoDimensionalArrayToSparseArray(int[][] originArray) {
        int availableNum = 0;
        for (int i = 0; i < originArray.length; i++) {
            for (int j = 0; j < originArray[i].length; j++) {
                if (originArray[i][j] != 0) {
                    availableNum ++;
                }
            }
        }
        int[][] sparseArray = new int[(availableNum+1)][3]; // ?填什么值 行-列-值
        // 第一行
        sparseArray[0][0] = originArray.length; // 6行
        sparseArray[0][1] = originArray[0].length; // 7列
        sparseArray[0][2] = availableNum; // 非0值数量
        /*// 第二行
        sparseArray[1][0] = 0;
        sparseArray[1][1] = 3;
        sparseArray[1][2] = 22;*/
        int row = 1;
        for (int i = 0; i < originArray.length; i++) {
            for (int j = 0; j < originArray[i].length; j++) {
                if (originArray[i][j] != 0) {
                    sparseArray[row][0] = i;
                    sparseArray[row][1] = j;
                    sparseArray[row][2] = originArray[i][j];
                    row++;
                }
            }
        }
        return sparseArray;
    }

    private static void printArray(int[][] originArray) {
        for (int i = 0; i < originArray.length; i++) {
            for (int j = 0; j < originArray[i].length; j++) {
                System.out.print(originArray[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
