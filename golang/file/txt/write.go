package main

import (
	"fmt"
	"os"
	"bufio"
)

func main() {
	path := "../data/output.txt"
	file, err := os.OpenFile(path, os.O_WRONLY|os.O_CREATE, 0666)
	if  err != nil {
		fmt.Printf("打开文本文件错误: %v", err)
		return
	}
	defer file.Close()

	content := "hello, golang\n"

	writer := bufio.NewWriter(file)
	for i := 0; i < 3; i++ {
		writer.WriteString(content)
	}

	writer.Flush()

	fmt.Println("写入成功!")
}
