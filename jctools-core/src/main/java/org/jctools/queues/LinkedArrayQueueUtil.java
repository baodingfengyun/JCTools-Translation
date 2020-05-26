package org.jctools.queues;

import org.jctools.util.InternalAPI;

import static org.jctools.util.UnsafeRefArrayAccess.REF_ARRAY_BASE;
import static org.jctools.util.UnsafeRefArrayAccess.REF_ELEMENT_SHIFT;

/**
 * Q: 什么是{@code LinkedArrayQueue}？
 * A: 由多个数组构成的队列，并有一个元素为下一个数组的指针。
 *
 * This is used for method substitution in the LinkedArray classes code generation.
 */
@InternalAPI
final class LinkedArrayQueueUtil
{
    static int length(Object[] buf)
    {
        return buf.length;
    }

    /**
     * This method assumes index is actually (index << 1) because lower bit is
     * used for resize. This is compensated for by reducing the element shift.
     * The computation is constant folded, so there's no cost.
     */
    static long modifiedCalcCircularRefElementOffset(long index, long mask)
    {
        return REF_ARRAY_BASE + ((index & mask) << (REF_ELEMENT_SHIFT - 1));
    }

    /**
     * 计算下一个数组的偏移量 - 数组的最后一个元素为下一个数组的指针。
     */
    static long nextArrayOffset(Object[] curr)
    {
        return REF_ARRAY_BASE + ((long) (length(curr) - 1) << REF_ELEMENT_SHIFT);
    }
}
