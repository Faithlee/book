package main

import (
	"fmt"
	"os"
	"bufio"
)

func main() {
	name := "../data/buffer.txt"
	content := "golang is powerful"

	file, err := os.OpenFile(name, os.O_RDWR|os.O_CREATE|os.O_APPEND, 0644)
	if err != nil {
		fmt.Println("打开文件失败:", err)
	}
	defer file.Close()

	buf := []byte(content)

	writer := bufio.NewWriter(file)
	if _, err := writer.Write(buf); err != nil {
		fmt.Println("数据写入失败!")
		return
	}
	if err := writer.Flush(); err != nil {
		fmt.Println("数据刷入磁盘失败!")
		return
	}

	fmt.Println("数据写入成功!")
}
