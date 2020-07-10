package main

import "fmt"

func main() {
	var n int = 0
	reply := &n
	Multiply(5, 10, reply)
	fmt.Printf("%d\n", *reply)
	fmt.Printf("%d\n", n)
}

func Multiply(a, b int, reply *int) {
	*reply = a * b
}
