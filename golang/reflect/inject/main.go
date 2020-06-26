package main

import (
	"fmt"
	"github.com/codegangsta/inject"
)

type S1 interface{}
type S2 interface{}

type Staff struct {
	Name string `inject`
	Company S1 `inject`
	Level S2 `inject`
	Age int `inject`
}

func main() {
	// 创建实例
	i := inject.New()

	i.Map("tom")
	i.MapTo("tencent", (*S1)(nil))
	i.MapTo("T4", (*S2)(nil))
	i.Map(23)

	// 反转调用
	i.Invoke(Format)

	// 对结构体注入
	s := Staff{}
	is := inject.New()
	is.Map("Tim")
	is.MapTo("alibaba", (*S1)(nil))
	is.MapTo("T5", (*S2)(nil))
	is.Map(28)
	is.Apply(&s)

	fmt.Printf("staff=%v\n", s)
}

func Format(name string, company S1, level S2, age int) {
	fmt.Printf("name=%s, company=%s, level=%s, age=%d\n", name, company, level, age)
}
