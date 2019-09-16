package com.line.datastructs.tree.huffmantree;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @desc 数据压缩
 * 原理分析：
 * （1）生成赫夫曼树
 *      i like like like java do you like a java
 * （2）生成赫夫曼树对应的编码
 *      =01,a=100 d=11000 u=11101 e=1110 v=11011 i=101 y=11010 j=0010 k=1111 l=000 o=0011
 * （3）使用赫夫曼编码来生成赫夫曼编码数据，即按照上面的赫夫曼编码，将"i like like ..."
 *      字符串生成对应的编码数据，形式如下：
 *
 * @Author xw
 * @Date 2019/9/16
 */
public class HuffmanZip {

    static Map<Byte, String> huffmanCodes = new HashMap<>(); // 赫夫曼编码集合
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        // （1）生成赫夫曼树
        String content = "i like like like java do you like a java";

        // （3）使用赫夫曼编码来生成赫夫曼编码数据，即按照上面的赫夫曼编码，将"i like like ..."
        //      字符串生成对应的编码数据，形式如下：
        byte[] huffmanCodesBytes = huffmanZip(content.getBytes());
        System.out.println("压缩后的结果：" + new String(huffmanCodesBytes) + " 长度=" + huffmanCodesBytes.length);
        // 解码
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("原字符串：" + new String(sourceBytes));

        String srcFile = "D:\\2019\\doc\\img\\huffman.png";
        String dstFile = "D:\\2019\\doc\\img\\huffman.zip";
        zipFile(srcFile, dstFile);
        System.out.println("ok");
    }

    public static void zipFile(String srcFile, String dstFile) {
        // 创建文件输入流
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oss = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);

            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oss = new ObjectOutputStream(os);
            oss.writeObject(huffmanBytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oss.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 解码
     * @param huffmanCodes 码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1.先得到huffmanBytes对应的二进制，形式101011000...
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是否为最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToString(!flag, b));
        }
        //System.out.println("赫夫曼字节数组对应的二进制字符串=" + stringBuilder.toString());
        String str = new String(huffmanBytes);

        // 2.value-key反转
        Map<String, Byte> map = new HashMap<>();
        huffmanCodes.entrySet().stream().forEach(m -> map.put(m.getValue(), m.getKey()));
        System.out.println(map);

        // 3.解码
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < str.length(); ) {
            // for (int i = 0; i < str.length(); i++) { // 这里的i++不要了!!! -> 下面有
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = str.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    System.out.println("key=" + key);
                    // 找到了
                    flag = false;
                }
            }
            list.add(b);
            i += count; // i 直接移动到 count
        }

        // 当for循环结束，就得到原字符串
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * byte转二进制字符串
     * @param flag true-需要补高位，flag-不需要补
     * @param b
     * @return
     */
    private static String byteToString(boolean flag, byte b) {
        int temp = b;
        // 补高位
        if (flag) {
            temp |= 256;
        }
        // 最后一位，不足8位，不需要补高位
        String str = Integer.toBinaryString(temp); // 转二进制
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> list = getCodes(bytes);
        Node root = createHuffmanTree(list);
        Map<Byte, String> codes = getCodes(root);
        return huffmanZip(codes, bytes);
    }

    private static byte[] huffmanZip(Map<Byte, String> codes, byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            stringBuilder.append(codes.get(bytes[i]));
        }
        return stringBuilder.toString().getBytes();
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            // 判断当前结点是叶子结点还是非叶子结点
            if (node.data == null) { // 非叶子结点
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { // 说明是一个叶子结点
                // 就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 生成赫夫曼树对应的编码
     * =01,a=100 d=11000 u=11101 e=1110 v=11011 i=101 y=11010 j=0010 k=1111 l=000 o=0011
     * @param bytes
     * @return
     */
    private static List<Node> getCodes(byte[] bytes) {
        Map<Byte, Integer> countMap = new HashMap<>();
        for (Byte b : bytes)
            countMap.put(b, Optional.ofNullable(countMap.get(b)).map(m -> m+1).orElse(1));
        return countMap.entrySet().stream().map(m -> new Node(m.getKey(), m.getValue())).collect(Collectors.toList());
    }

    // 创建赫夫曼树的方法
    private static Node createHuffmanTree(List<Node> nodes) {
        // 循环（最后只剩下一个结点）
        while (nodes.size() > 1) {
            // （1）从小到大进行排序，将每一个数据（每个结点）可以看成是一颗最简单的二叉树
            Collections.sort(nodes);
            // （2）取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 创建一个新的二叉树，没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // （3）从list中删除已处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // （4）将parent加入到list
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
