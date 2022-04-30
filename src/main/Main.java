package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	private static class Node {
		private List<Node> listChildren = new ArrayList<>();
		private int value;

		Node(List<Node> listChildren, int value) {
			this.listChildren.addAll(listChildren);
			this.value = value;
		}

		List<Node> getChildren() {
			return listChildren;
		}

		int getValue() {
			return value;
		}
	}

	private static class TraverseResult {
		private int count;
		private long totalValue;
		private long totalOddValue;

		void count() {
			count++;
		}
	}

	public static void main(String[] args) {
		Node root = createTree();

		System.out.println("Count = " + getNodesCount(root));
		System.out.println("Total odd value = " + getSumOfOddValue(root));
		System.out.println("Average = " + getAverage(root));
	}

	private static int getNodesCount(Node node) {
		TraverseResult result = traverseTree(node, "countNode");
		return result.count;
	}

	private static long getSumOfOddValue(Node node) {
		TraverseResult result = traverseTree(node, "sumOddValues");
		return result.totalOddValue;
	}

	private static double getAverage(Node node) {
		TraverseResult result = traverseTree(node, "average");
		return (double) result.totalValue / result.count;
	}

	private static TraverseResult traverseTree(Node root, String processType) {
		if (root == null) {
			return null;
		}

		Stack<Node> stack = new Stack<>();
		stack.push(root);

		TraverseResult result = new TraverseResult();
		while (!stack.empty()) {
			Node top = stack.pop();
			for (Node child : top.getChildren()) {
				stack.push(child);
			}
			getNewValue(top, result, processType);
		}

		return result;
	}

	private static void getNewValue(Node node, TraverseResult result, String processType) {
		switch (processType) {
			case "countNode":
				result.count();
				break;
			case "sumOddValues":
				if (node.getValue() % 2 != 0) {
					result.totalOddValue += node.getValue();
				}
				break;
			case "average":
				result.count();
				result.totalValue += node.value;
				break;
		}
	}

	/*
	 *                      2
	 *            3         1            5
	 *         6    8      10         2     0
	 *             3  2             7 6 5    3
	 *                                        1
	 */
	private static Node createTree() {
		List<Node> childrenLevel5 = List.of(new Node(List.of(), 1));
		List<Node> childrenLevel4_1 = List.of(
				new Node(List.of(), 7),
				new Node(List.of(), 6),
				new Node(List.of(), 5)
		);
		List<Node> childrenLevel4_2 = List.of(
				new Node(List.of(), 3),
				new Node(List.of(), 2)

		);
		List<Node> childrenLevel4_3 = List.of(
				new Node(childrenLevel5, 3)
		);

		List<Node> childrenLevel3_1 = List.of(
				new Node(List.of(), 6),
				new Node(childrenLevel4_2, 8)
		);

		List<Node> childrenLevel3_2 = List.of(
				new Node(List.of(), 10)
		);

		List<Node> childrenLevel3_3 = List.of(
				new Node(childrenLevel4_1, 2),
				new Node(childrenLevel4_3, 0)
		);

		List<Node> childrenLevel2_1 = List.of(
				new Node(childrenLevel3_1, 3),
				new Node(childrenLevel3_2, 1),
				new Node(childrenLevel3_3, 5)
		);

		return new Node(childrenLevel2_1, 2);
	}
}
