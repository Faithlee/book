package main

import "fmt"

func main() {
	x := min(1,3,2,0)
	fmt.Println("the minimum is", x)

	// 对slice进行解包
	slice := []int{7,9,5,3,1}
	x = min(slice...)
	fmt.Println("the mininum in the slice is", x)
}

func min(s ...int) int {
	if len(s) == 0 {
		return 0
	}
	min := s[0]
	for _, v := range s {
		if v < min {
			min = v
		}
	}

	return min
}
