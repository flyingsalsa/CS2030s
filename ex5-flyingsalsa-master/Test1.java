import cs2030s.fp.Maybe;

class Test1 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expectCompile("Maybe<Object> m = new Maybe<>() should not compile",
        "cs2030s.fp.Maybe<Object> m = new cs2030s.fp.Maybe<>();", false);
    i.expectCompile("Maybe.None m; should not compile",
        "cs2030s.fp.Maybe.None m;", false);
    i.expectCompile("Maybe.Some<Object> m; should not compile",
        "cs2030s.fp.Maybe.Some<Object> m;", false);

    i.expectCompile("Maybe<Object> m = Maybe.none() should compile",
        "cs2030s.fp.Maybe<Object> m = cs2030s.fp.Maybe.none()", true);

    i.expectCompile("Maybe.none().get(); should not compile",
        "cs2030s.fp.Maybe.none().get();", false);
    i.expectCompile("Maybe.some(0).get(); should not compile",
        "cs2030s.fp.Maybe.some(0).get();", false);

    i.expect("Maybe.none() returns []",
        Maybe.none().toString(), "[]");
    i.expectCompile("Maybe<Integer> m = Maybe.some(null) should compile",
        "cs2030s.fp.Maybe<Integer> m = cs2030s.fp.Maybe.some(null)", true);
    i.expect("Maybe.some(null) returns [null]",
        Maybe.some(null).toString(), "[null]");
    i.expectCompile("Maybe<Integer> m = Maybe.some(4) should compile",
        "cs2030s.fp.Maybe<Integer> m = cs2030s.fp.Maybe.some(4)", true);
    i.expect("Maybe.some(4) returns [4]",
        Maybe.some(4).toString(), "[4]");
    i.expect("Maybe.none() == Maybe.none() returns true",
        Maybe.none() == Maybe.none(), true);
    i.expect("Maybe.none().equals(Maybe.none()) returns true",
        Maybe.none().equals(Maybe.none()), true);
    i.expect("Maybe.none().equals(Maybe.some(\"day\")) returns false",
        Maybe.none().equals(Maybe.some("day")), false);
    i.expect("Maybe.none().equals(Maybe.some(null)) returns false",
        Maybe.none().equals(Maybe.some(null)), false);
    i.expect("Maybe.some(\"day\").equals(Maybe.some(\"day\")) returns true",
        Maybe.some("day").equals(Maybe.some("day")), true);
    i.expect("Maybe.some(null).equals(Maybe.some(\"day\")) returns false",
        Maybe.some(null).equals(Maybe.some("day")), false);
    i.expect("Maybe.some(null).equals(Maybe.some(null)) returns true",
        Maybe.some(null).equals(Maybe.some(null)), true);
    i.expect("Maybe.some(null).equals(Maybe.none()) returns false",
        Maybe.some(null).equals(Maybe.none()), false);
    i.expect("Maybe.some(null).equals(null) returns false",
        Maybe.some(null).equals(null), false);
    i.expect("Maybe.none().equals(null) return false",
        Maybe.none().equals(null), false);

    i.expect("Maybe.of(null).equals(Maybe.none()) returns true",
        Maybe.of(null).equals(Maybe.none()), true);
    i.expect("Maybe.of(null).equals(Maybe.some(null)) returns false",
        Maybe.of(null).equals(Maybe.some(null)), false);
    i.expect("Maybe.of(4).equals(Maybe.none()) returns false",
        Maybe.of(4).equals(Maybe.none()), false);
    i.expect("Maybe.of(4).equals(Maybe.some(4)) returns true",
        Maybe.of(4).equals(Maybe.some(4)), true);
  }
}
