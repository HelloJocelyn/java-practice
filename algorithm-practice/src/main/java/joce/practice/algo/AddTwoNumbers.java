package joce.practice.algo;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        long a = 9999999991l;
        long b = 9;
        System.out.println((a + b));
        ListNode l1 = new ListNode(5);
        l1.next=new ListNode(6);
        l1.next.next=new ListNode(4);
//        ListNode node2 = new ListNode(4);
//        ListNode node3 = new ListNode(3);
//        l1.next = node2;
//        node2.next = node3;

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(0);
        l2.next.next = new ListNode(0);
        l2.next.next.next = new ListNode(0);
        l2.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(0);
        l2.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(1);
//        ListNode node22 = new ListNode(6);
//        ListNode node33 = new ListNode(4);
//        l2.next = node22;
//        node22.next = node33;

//        do {
//            System.out.println(listNode.value);
//            listNode = listNode.nextNode;
//        } while (listNode.nextNode != null);
        final ListNode listNode = addTwoNumbers2(l1, l2);
        System.out.println(listNode.toString());


    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int init = l1.val + l2.val;
        int carry = init / 10;
        ListNode result = new ListNode(init % 10);
        ListNode previous = result;
        while (l1.next != null) {
            l1 = l1.next;
            int val = 0;
            if (l2.next != null) {
                l2 = l2.next;
                val = carry + l1.val + l2.val;
            } else {
                val = carry + l1.val;
            }

            ListNode next = new ListNode(val % 10);
            previous.next = next;
            previous = next;
            carry = val / 10;
        }
        while (l2.next != null || carry != 0) {
            int val = 0;
            if (l2.next != null) {
                l2=l2.next;
                val = carry + l2.val;

            } else {
                val = carry;
            }
            ListNode next = new ListNode(val % 10);
            previous.next = next;
            previous = next;
            carry = val / 10;
        }
        return result;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long i = 1;
        long result1 = 0;
        ListNode nextNode1 = l1;
        while (nextNode1 != null) {
            result1 += i * nextNode1.val;
            i *= 10;
            nextNode1 = nextNode1.next;
        }

        long j = 1;
        long result2 = 0;
        ListNode nextNode2 = l2;
        while (nextNode2 != null) {
            result2 += j * nextNode2.val;
            j *= 10;
            nextNode2 = nextNode2.next;
        }
        long result = result1 + result2;
        ListNode listNode;
        if (result >= 10) {
            listNode = new ListNode((int) (result % 10));
            result /= 10;
        } else {
            return new ListNode((int) result);
        }
        ListNode lastNode = listNode;
        while (result >= 10) {
            ListNode nextNode = new ListNode((int) (result % 10));
            result = result / 10;
            lastNode.next = nextNode;
            lastNode = nextNode;
        }
        lastNode.next = new ListNode((int) result);
        return listNode;
    }


//    static class ListNode {
//        int value;
//        ListNode previouseNode;
//        ListNode nextNode;
//
//        ListNode() {
//        }
//
//        ListNode(int value) {
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            final StringBuilder stringBuilder = new StringBuilder("[");
//            stringBuilder.append(value);
//            ListNode nextNode = this.nextNode;
//            while (nextNode != null) {
//                stringBuilder.append(",").append(nextNode.value);
//                nextNode = nextNode.nextNode;
//            }
//            stringBuilder.append("]");
//            return stringBuilder.toString();
//        }
//    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(val);
        ListNode nextNode = this.next;
        while (nextNode != null) {
            stringBuilder.append(",").append(nextNode.val);
            nextNode = nextNode.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
