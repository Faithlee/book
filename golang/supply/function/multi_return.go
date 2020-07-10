package main

import "fmt"

func main() {
	a, b := getX2AndX3(10)
	fmt.Println(a, b)

	a, b = getX2AndX3_2(10)
	fmt.Println(a, b)
}

func getX2AndX3(input int) (int, int) {
	return 2 * input, 3 * input
}

func getX2AndX3_2(input int) (x, y int) {
	x = input * 2
	y = input * 3
	return
}
