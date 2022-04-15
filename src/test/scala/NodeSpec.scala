import graph.Node
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class NodeSpec extends AnyFlatSpec with should.Matchers {
  behavior of "Node.toString"

  it should "return textual representation for a given graph" in {
    val project1 = Node("Project1")
    val project2 = Node("Project2")
    val agg = Node("Aggregate", Set(project1, project2))
    val union = Node("Union", Set(agg, project2))
    val exchange = Node("Exchange", Set(agg, project1))
    val plan = Node("Plan", Set(exchange, union))

    val actual = plan.toString
    val expected = """Plan
                     |+- Exchange
                     |   +- Aggregate
                     |      +- Project1
                     |      +- Project2
                     |   +- Project1
                     |+- Union
                     |   +- Aggregate
                     |      +- Project1
                     |      +- Project2
                     |   +- Project2
                     |""".stripMargin.filter(_ != '\r')
    actual shouldBe expected
  }

  behavior of "Node.add"

  it should "return a node with added child for different node than this" in {
    val project1 = Node("Project1")
    val project2 = Node("Project2")
    val union = Node("Union", Set(project1))
    val actual = union.add(project2)

    actual shouldBe Node("Union", Set(project1, project2))
  }

  it should "return the same node for  this node" in {
    val node = Node("Node")
    node.add(node) shouldBe node
  }

  behavior of "Node.remove"

  it should "return a node with removed child for child node" in {
    val project1 = Node("Project1")
    val union = Node("Union", Set(project1))
    union.remove(project1) shouldBe Node("Union")
  }

}
