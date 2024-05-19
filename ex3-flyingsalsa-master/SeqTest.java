class SeqTest {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    Seq<Integer> seq = new Seq<Integer>(4);
    i.expect("initializing an empty sequence",
        seq.toString(), "[ 0:null, 1:null, 2:null, 3:null ]");
    seq.set(0, 3);
    i.expect("set element 0 to 3",
        seq.toString(), "[ 0:3, 1:null, 2:null, 3:null ]");
    seq.set(1, 4);
    i.expect("set element 1 to 4",
        seq.toString(), "[ 0:3, 1:4, 2:null, 3:null ]");
    seq.set(2, 1);
    i.expect("set element 2 to 1",
        seq.toString(), "[ 0:3, 1:4, 2:1, 3:null ]");
    seq.set(3, 6);
    i.expect("set element 3 to 6",
        seq.toString(), "[ 0:3, 1:4, 2:1, 3:6 ]");
    i.expect("get element 0",
        seq.get(0), 3);
    i.expect("get element 1",
        seq.get(1), 4);
    i.expect("get element 2",
        seq.get(2), 1);
    i.expect("get element 3",
        seq.get(3), 6);

    i.expect("smallest element is 1",
        seq.min(), 1);
    i.expect("seq.min() does not change the sequence",
        seq.toString(), "[ 0:3, 1:4, 2:1, 3:6 ]");
    seq.set(2, 9);
    i.expect("update element 2 to 9",
        seq.toString(), "[ 0:3, 1:4, 2:9, 3:6 ]");
    i.expect("smallest element is now 3",
        seq.min(), 3);

    i.expectCompile("cannot put a non-integer into a sequence of integer",
        "new Seq<Integer>(4).set(0, false)", false);

    i.expectCompile("cannot get a non-integer from a sequence of integer",
        "String s = new Seq<Integer>(4).get(0)", false);

    i.expectCompile("cannot create a sequence of non-comparable element", 
        "class A {} Seq<A> a;", false);

    i.expectCompile("cannot create a sequence of comparable element (but not to itself)", 
        "class A implements Comparable<Long> {" +
        "  public int compareTo(Long i) {" +
        "    return 0; " +
        "  }" +
        "}" +
        "Seq<A> a;", false);

    i.expectCompile("can create a sequence of comparable element (to itself)", 
        "class A implements Comparable<A> {" +
        "  public int compareTo(A i) {" +
        "    return 0; " +
        "  }" +
        "}" +
        "Seq<A> a;", true);
  }
}
