package main

import "fmt"
import "runtime"

func main() {
	cpuNum:= runtime.NumCPU()
	fmt.Println("cpu核心数:", cpuNum)

	runtime.GOMAXPROCS(cpuNum)
}
