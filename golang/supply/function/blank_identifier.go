package main

import "fmt"

func main() {
	i,_,f := ThreeValues()
	fmt.Printf("%d %f\n", i, f)
}

func ThreeValues() (int, int, float32) {
	return 1, 2, 3.14
}
