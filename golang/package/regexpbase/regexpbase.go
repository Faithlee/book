package regexpbase

import (
	"fmt"
	"regexp"
	"strconv"
)

func MatchStr() {
	fmt.Println("  匹配字符串")
	str := "abc azc a7c aac 888 a9c  tac"

	// 先解析正则，返回解释器
	// 1.匹配字符串
	//parser := regexp.MustCompile(`a.c`)
	// 2.匹配包含一个数字的字符串
	//parser := regexp.MustCompile(`a[0-9]c`)
	parser := regexp.MustCompile(`a\dc`)

	if parser == nil {
		fmt.Println("	regexp compile error")
		return
	}

	result := parser.FindAllStringSubmatch(str, -1)
	fmt.Println("	result=", result)
	fmt.Println()
}

// 匹配小数
func MatchFloat() {
	fmt.Println("  匹配小数:")
	str := "43.14 567 agsdg 1.23 7. 8.9 1sdljgl 6.66 7.8   "

	parser := regexp.MustCompile(`\d+\.\d+`)
	if parser == nil {
		fmt.Println("regexp complile error!")
		return
	}

	//result := parser.FindAllString(str, -1)
	result := parser.FindAllStringSubmatch(str, -1)
	fmt.Println("	regexp result=", result)
	fmt.Println()
}

func MatchWebTag() {
	fmt.Println("  匹配网页:")
	html := `
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Go语言入门教程</title>
</head>
<body>
    <div>Go语言简介</div>
    <div>Go语言基本语法
    Go语言变量的声明
    Go语言教程简明版
    </div>
    <div>Go语言容器</div>
    <div>Go语言函数</div>
</body>
</html>
	`
	parser := regexp.MustCompile(`<div>(?s:(.*?))</div>`)
	if parser == nil {
		fmt.Println("	regexp compile error!")
		return
	}

	result := parser.FindAllStringSubmatch(html, -1)
	for _, text := range result {
		fmt.Println("	text[1]=", text)
	}
	fmt.Println()
}

func MatchAndReplace() {
	fmt.Println("  匹配并且替换操作:")
	str := "John: 2578.34 William: 4567.23 Steve: 5632.18"
	pattern := "[0-9]+.[0-9]+"

	if ok, _ := regexp.Match(pattern, []byte(str)); ok {
		fmt.Println("	Match Found")
	}

	parser, _ := regexp.Compile(pattern)
	result := parser.ReplaceAllString(str, "##.##")
	fmt.Println("	result=", result)

	result1 := parser.ReplaceAllStringFunc(str, func(s string) string {
		v, _ := strconv.ParseFloat(s, 32)
		return strconv.FormatFloat(v * 2, 'f', 2, 32)
	})
	fmt.Println("	result1=", result1)
	fmt.Println()
}
