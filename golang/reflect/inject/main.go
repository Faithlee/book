package main

import (
	"fmt"
	"github.com/codegangsta/inject"
)

type S1 interface{}
type S2 interface{}

func main() {
	// 创建实例
	i := inject.New()

	i.Map("tom")
	i.MapTo("tencent", (*S1)(nil))
	i.MapTo("T4", (*S2)(nil))
	i.Map(23)

	// 反转调用
	i.Invoke(Format)
}

func Format(name string, company S1, level S2, age int) {
	fmt.Printf("name=%s, company=%s, level=%s, age=%d\n", name, company, level, age)
}
