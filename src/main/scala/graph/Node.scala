package graph

case class Node(name: String, children: Set[Node] = Set.empty) {
  def add(node: Node): Node = if (node == this) this else Node(name, children + node)

  def remove(node: Node): Node = Node(name, children - node)

  def display(): Unit = println(toString)

  override def toString: String = {
    def graphToString(children: Set[Node], visitedNodes: Set[Node], offset: Int, sb: StringBuilder): String = {
      val nodesToVisit = children.diff(visitedNodes)
      for (node <- nodesToVisit) {
        sb ++= " " * offset ++= "+- " ++= node.name ++= "\n"
        graphToString(node.children, visitedNodes + node, offset + 3, sb)
      }

      sb.toString()
    }

    graphToString(children, Set(this), 0, new StringBuilder(name + "\n"))
  }

}
