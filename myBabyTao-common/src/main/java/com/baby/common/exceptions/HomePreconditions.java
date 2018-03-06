package com.baby.common.exceptions;

import java.util.NoSuchElementException;

/**
 * HomePreconditions 基于 gdata-java-client,
 * 地址：https://code.google
 * .com/p/gdata-java-client/source/browse/trunk/java/src/com/google/gdata/util/common/base/Preconditions.java
 * 这里将所有的异常封装为QmmBaseException，并加入合适的CommonResponseCode
 */

/**
 * Simple static methods to be called at the start of your own methods to verify
 * correct arguments and state. This allows constructs such as
 * <pre>
 *     if (count <= 0) {
 *       throw new IllegalArgumentException("must be positive: " + count);
 *     }</pre>
 *
 * to be replaced with the more compact
 * <pre>
 *     checkArgument(count > 0, "must be positive: %s", count);</pre>
 *
 * Note that the sense of the expression is inverted; with {@code Preconditions}
 * you declare what you expect to be <i>true</i>, just as you do with an
 * <a href="http://java.sun.com/j2se/1.5.0/docs/guide/language/assert.html">
 * {@code assert}</a> or a JUnit {@code assertTrue()} call.
 *
 * <p>Take care not to confuse precondition checking with other similar types
 * of checks! Precondition exceptions -- including those provided here, but also
 * {@link IndexOutOfBoundsException}, {@link NoSuchElementException}, {@link
 * UnsupportedOperationException} and others -- are used to signal that the
 * <i>calling method</i> has made an error. This tells the caller that it should
 * not have invoked the method when it did, with the arguments it did, or
 * perhaps <i>ever</i>. Postcondition or other invariant failures should not
 * throw these types of exceptions.
 */
public final class HomePreconditions {
    private HomePreconditions() {
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the
     * calling method.
     *
     * @param expression a boolean expression
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression) throws Exception {
        if (!expression) {
            throw new BaseException();
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the
     * calling method.
     *
     * @param expression   a boolean expression
     * @param errorMessage the exception message to use if the check fails; will
     *                     be converted to a string using {@link String#valueOf(Object)}
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression,
                                     Object errorMessage) throws BaseException {
        if (!expression) {
            throw new BaseException(String.valueOf(errorMessage));
        }
    }


}
