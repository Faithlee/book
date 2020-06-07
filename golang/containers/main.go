package main

import "fmt"

func main(){
	// 1.数组基础
	baseArray()

	// 2.多维数组
	multiArray()

	// 3.切片
	sliceArray()

	// 4.切片添加元素
	sliceAppend()

	// 5.切片复制
	sliceCopy()

	// 6.切片删除
	sliceDelete()
}

func baseArray(){
	// 定义长度为3的数组
	fmt.Println("定义数组:")
	var a [3]int
	fmt.Println(a[0])
	fmt.Println(a[len(a)-1])

	// 打印数组
	fmt.Println("打印数组:")
	for i, v := range a {
		fmt.Printf("%d %d\n", i, v)
	}
	fmt.Println()

	// 只打印元素
	for _, v := range a {
		fmt.Printf("%d\n", v)
	}
	fmt.Println()

	// 数组初始化，使用相应类型的默认值填充
	fmt.Println("数组初始化:")
	var i [3]int = [3]int{1, 2, 3}
	var j [3]int = [3]int{1, 2}
	// 编译器自动处理数组长度
	k := [...]int{1, 2, 3}
	fmt.Println(i[2])
	fmt.Println(j[2])
	fmt.Println(k[2])
	fmt.Println()

	// 注意数组长度是数组类型的一部分，[3]int和[4]int是不同的类型
	// 数组比较：只能是相同类型才可以比较，否则会编译错误
	fmt.Println("数组比较:")
	x := [2]int{1, 2}
	y := [...]int{1, 2}
	z := [2]int{3, 4}
	fmt.Println(x == y, x == z, y == z)


	fmt.Println("数组遍历:")
	var family [3]string
	family[0] = "dad"
	family[1] = "mum"
	family[2] = "baby"
	for k, v := range family {
		fmt.Println(k, v)
	}
	fmt.Println()
}

func multiArray() {
	// 多维数组赋值
	var array [4][2]int;
	array = [4][2]int{{1,2}, {3,4}, {5,6}, {7,8}}
	for _,j := range array {
		for _,n := range j {
			fmt.Printf("%d ", n)
		}
		fmt.Println()
	}

	// 初始化索引1,3的元素
	array = [4][2]int{1:{10,11}, 3:{12, 13}}
	// 初始化指定的元素
	array = [4][2]int{1:{0:20}, 3:{1: 30}}
	for _,j := range array {
		for _,n := range j {
			fmt.Println(n)
		}
	}

	// 类型一致的数组可以相互赋值
	var array1 [2][2]int
	var array2 [2][2]int
	array2[0][0] = 10
	array2[0][1] = 20
	array2[1][0] = 30
	array2[1][1] = 40
	array1 = array2
	for _,j := range array1 {
		for _,n := range j {
			fmt.Printf("%d ", n)
		}
		fmt.Println()
	}

	// 使用索引为多维数组赋值
	//var array3 [2]int = array1[0] 
}

func sliceArray() {
	// 切片：从数组或切片生成新的切片
	var a = [3]int{1, 2, 3}
	fmt.Println(a)
	fmt.Println(a[1:2])

	// 切片的特性:
	var nums [30]int
	for i := 0; i < 30; i++ {
		nums[i] = i+1
	} 
	// 切片区间 slice[n:m]
	fmt.Println("切片区间:", nums[10:20])
	fmt.Println("切片到尾部:", nums[20:])
	fmt.Println("切片开头到指定位置:", nums[:5])
	// 空切片，即复位
	fmt.Println("切片复位:", nums[0:0])
	// 表示原切片
	fmt.Println("原切片:", nums[:])
	fmt.Println()

	// 声明新的切片
	var strList []string
	var numList []int
	var numListEmpty = []int{}
	fmt.Println("直接声明新切片:", strList, numList, numListEmpty)
	fmt.Println("直接声明新切片大小:", len(strList), len(numList), len(numListEmpty))
	fmt.Println("判断切片结果:", strList == nil, numList == nil, numListEmpty == nil)
	// todo 新切片使用append添加元素
	numList = append(numList, 1)
	numList = append(numList, 2)
	fmt.Println("append添加元素:", numList)
	fmt.Println()

	// make构造切片
	arr1 := make([]int, 2)
	arr2 := make([]int, 2, 10)
	fmt.Println("make构造切片:", arr1, arr2)
	fmt.Println("make构造切片长度:", len(arr1), len(arr2))
	fmt.Println()
}

func sliceAppend() {
	// 切片动态添加元素
	var a []int
	a = append(a, 1)
	// 添加多个元素，解包？
	a = append(a, 2, 3, 4)
	// 添加一个切片，切片需要解包?
	a = append(a, []int{5,6,7}...)
	fmt.Println("切片添加元素:", a)

	// 在切片开头添加元素
	var b = []int{4,5,6}
	b = append([]int{1,2,3}, b...)
	b = append([]int{0}, b...);
	fmt.Println("切片开头添加元素:", b)

	// 切片的扩容：按照1，2，4，8等扩充
	fmt.Println("切片扩容:")
	var nums []int
	for i := 0; i < 10; i++ {
		nums = append(nums, i)
		fmt.Printf("len: %2d, cap: %2d, pointer: %p\n", len(nums), cap(nums), nums)
	}
	fmt.Println()
}

func sliceCopy() {
	slice1 := []int{1,2,3,4,5}
	slice2 := []int{5,4,3}
	slice3 := []int{5,4,3}
	// 只会复制前三个元素
	var n = copy(slice2, slice1)
	fmt.Println("slice2复制的个数:", n, slice2)
	n = copy(slice1, slice3)
	fmt.Println("slice1复制的个数:", n, slice1)

	// 切片引用及复制
	const count = 100

	src := make([]int, count)
	for i := 0; i < count; i++ {
		src[i] = i
	}

	// 引用切片
	ref := src

	// 预分配足够元素的切片
	cp := make([]int, count)
	copy(cp, src)

	src[0] = 99
	fmt.Println("引用切片的第一个元素:", ref[0])
	fmt.Println("复制切片的第一个元素和最后一个元素", cp[0], cp[count-1])

	copy(cp, src[4:6])
	for i:=0; i<5; i++ {
		fmt.Printf("%d ", cp[i])
	}

	fmt.Println()
	fmt.Println()
}

func sliceDelete(){
	// 切片删除元素：将删除点前后的移动到新的位置
	idx := 2
	str := []string{"a", "b", "c", "d", "e"}
	fmt.Println("切片删除位置2的元素:", str, str[:idx], str[idx+1:])
	newStr := append(str[:idx], str[idx+1:]...)
	fmt.Println("删除后的元素重新连接:", newStr)
	fmt.Println()

	var a = []int{1,2,3}
	// 从开头删除：通过移动指针或原地完成(append)
	// 从中间删除
	// 从尾部删除
	fmt.Println(a)
}
