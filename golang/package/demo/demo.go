package demo

import "fmt"

type Hero struct {
	Name string
	Kind int
}

func (hero Hero) Attack() {
	fmt.Printf("%s is attacking!\n", hero.Name)
}

func (hero Hero) Defend() {
	fmt.Printf("%s is Defending!\n", hero.Name)
}
