package graph

object GraphApp extends App{
  val project1 = Node("Project1")
  val project2 = Node("Project2")
  val agg = Node("HashAggregate", Set(project1, project2))
  val union = Node("Union", Set(agg, project2))
  val exchange = Node("Exchange", Set(agg, project1))
  val plan = Node("Plan", Set(exchange, union))
  plan.display()
}
