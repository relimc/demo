class MySet<T> (val helperSet: HashSet<T>) : Set<T> {
    override val size: Int
        get() = helperSet.size

    override fun isEmpty() = helperSet.isEmpty()

    override fun iterator() = helperSet.iterator()

    override fun containsAll(elements: Collection<T>) = helperSet.containsAll(elements)

    override fun contains(element: T) = helperSet.contains(element)
}