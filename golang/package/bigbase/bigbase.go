package bigbase

import (
	"fmt"
	"math/big"
	"time"
)

func Uint64() {
	fmt.Println("  uint64:")

	big1 := new(big.Int).SetUint64(uint64(1000))
	fmt.Println("	big1 is", big1)

	big2 := big1.Uint64()
	fmt.Println("	big2 is", big2)

	fmt.Println("  int64")
	big3 := new (big.Int).SetInt64(int64(10000))
	fmt.Println("	big3 is", big3)
}

const  LIMIT = 100
var fibs [LIMIT]*big.Int

func fibonacci(n int) (res *big.Int) {
	if n <= 1 {
		res = big.NewInt(1)
	} else {
		tmp := new(big.Int)
		res = tmp.Add(fibs[n-1], fibs[n-2])
	}

	fibs[n] = res

	return
}

func CalculateFibonacci() {
	result := big.NewInt(0)
	start := time.Now()
	for i := 0; i < LIMIT; i++ {
		result = fibonacci(i)
		fmt.Printf("	第%d位的值: %d\n", i+1, result)
	}
	end := time.Now()
	delta := end.Sub(start)
	fmt.Printf("	执行耗时: %s\n", delta)
}
