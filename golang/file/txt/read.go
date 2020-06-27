package main

import (
	"fmt"
	"os"
	"io"
	"bufio"
)

func main() {
	path := "../data/output.txt"

	file, err := os.Open(path)
	if err != nil {
		fmt.Println("打开文件失败:", err)
		return
	}

	defer file.Close()

	reader := bufio.NewReader(file)
	for {
		line, err := reader.ReadString('\n')
		if err == io.EOF {
			break
		}
		fmt.Print(line)
	}

	fmt.Println("文件读取完成")
}
