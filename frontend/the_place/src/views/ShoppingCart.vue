<template>
<v-app class="cart_app"
        app 
        id="app"
        dark>
        <v-row>
           <v-card color="black" width="100%">
            <v-card dark color="black" class="mt-4">
                <v-row>
                    <v-card-title class="title text-h6 text-md-h5 text-lg-h4">КОРЗИНА</v-card-title>
                </v-row>
                <v-row v-if="update_total_price(cart) > 0"> 
                    <v-col cols="8">
                        <v-card class="mx-auto" max-width="80%" color="black">
                            <v-card-text>
                                <v-row v-for="item in cart"
                                            :key="item"
                                            md="4">
                                            <v-col>
                                                <v-card color="black" v-if="item.quantity > 0">
                                                    <v-row>
                                                        <v-col cols="1">
                                                            <v-card class="mt-10" color="black">
                                                                <div class="d-flex justify-space-around">                                                                        
                                                                    <v-btn v-if="item.quantity-1 >= 0"
                                                                                icon 
                                                                                v-bind="props"
                                                                                target="_blank"
                                                                                @click="item.quantity = 0, delete_cart_item(item.cartItemId)"
                                                                                > 
                                                                        <v-img color="#D3CDBD" 
                                                                            class="ma-2" 
                                                                            src="../assets/close.svg"/>
                                                                   </v-btn>
                                                                </div>
                                                            </v-card>
                                                        </v-col>
                                                        <v-col cols="2">
                                                            <!-- :src="require('../assets/' + item.imageUrl + '.png')" -->
                                                            <v-img id="item_image" 
                                                                :src="item.imageUrl"
                                                                />
                                                        </v-col>
                                                        <v-col cols="5">
                                                            <v-text class="item_title_of_dish_cart" >{{item.name}}</v-text>
                                                        </v-col>
                                                        <v-col cols="2"> 
                                                            <v-btn v-if="item.quantity >= 0"
                                                                    rounded="xl"
                                                                    class="plus_minus_outline text-none text-subtitle"
                                                                    variant="flat"  
                                                                    color="black"  
                                                                    size="small"
                                                                    density="compact"
                                                                    >
                                                                    <v-btn v-if="item.quantity-1 > 0"
                                                                        icon 
                                                                        target="_blank"
                                                                        @click="item.quantity--"
                                                                        >
                                                                        <v-img color="#D3CDBD" 
                                                                               class="ma-2" 
                                                                               src="../assets/minus_outline.svg"/>
                                                                    </v-btn>
                                                                    <v-btn v-if="item.quantity-1 == 0"
                                                                        icon 
                                                                        target="_blank"
                                                                        
                                                                        @click="delete_cart_item(item.cartItemId)"
                                                                        >
                                                                        <v-img color="#D3CDBD" 
                                                                               class="ma-2" 
                                                                               src="../assets/minus_outline.svg"/>
                                                                    </v-btn>
                                                                    <v-text class="item_title_of_dish_cart" color="#D3CDBD" >
                                                                    {{item.quantity}}
                                                                    </v-text>
                                                                    <v-btn v-if="item.quantity >= 0 & item.quantity < stock_quantity"
                                                                        icon 
                                                                        target="_blank"
                                                                        @click="item.quantity++"
                                                                        >
                                                                    <template>
                                                                    <v-img color="#D3CDBD"
                                                                               class="ma-2" 
                                                                               src="../assets/plus_outline.svg"/>
                                                                    </template>
                                                                    </v-btn>
                                                                    <v-btn v-if="item.quantity >= stock_quantity"
                                                                        icon 
                                                                        disabled
                                                                        target="_blank"
                                                                        >
                                                                    <template>
                                                                    <span color="#D3CDBD">+</span>
                                                                    </template>
                                                                    </v-btn>
                                                                    </v-btn>
                                                        </v-col>
                                                        <v-col cols="2">
                                                            <v-row>
                                                            <v-col>
                                                            <v-text class="item_title_of_dish_cart">{{item.price}} ₽</v-text>
                                                             </v-col>
                                                            </v-row>
                                                            <v-row>
                                                            <v-col>
                                                            <v-text class="item_total_price_cart">{{item.quantity * item.price}} ₽</v-text>
                                                             </v-col>
                                                            </v-row>
                                                        </v-col>
                                                    </v-row>
                                                    <v-divider color="#D3CDBD" class="mt-2 mb-2"></v-divider>
                                                </v-card>
                                            </v-col>
                                        </v-row>
                            </v-card-text>
                        </v-card>
                    </v-col>
                    <v-divider color="#D3CDBD" class="mt-2 mb-2" vertical></v-divider>
                    <v-col cols="4">
                        <v-card class="mx-auto" max-width="70%" color="black">
                            <v-card-title class="order_title">СУММА ЗАКАЗА</v-card-title>
                                <v-divider color="#D3CDBD" class="mt-9 mb-8"></v-divider>
                                <v-card-text>
                                    <v-row>
                                        <v-col cols="9">
                                            <v-text class="order_title">Товары в заказе</v-text>
                                        </v-col>
                                        <v-col cols="3">
                                            <v-text class="order_title">{{update_total_price(cart)}} ₽</v-text>
                                        </v-col>
                                    </v-row>
                                <v-row>
                                        <v-col cols="9">
                                            <v-text class="order_title">Доставка</v-text>
                                        </v-col>
                                        <v-col cols="3">
                                            <v-text class="order_title">{{delivery_price}} ₽</v-text>
                                        </v-col>
                                    </v-row>
                                </v-card-text>
                                <v-divider color="#D3CDBD" class="mt-8 mb-8"></v-divider>
                                <v-card-text>
                                    <v-row>
                                        <v-col cols="9">
                                            <v-text class="order_title bold">Итого</v-text>
                                        </v-col>
                                        <v-col cols="3">
                                            <v-text class="order_title">{{update_total_price(cart) + delivery_price}} ₽</v-text>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col>
                                        <v-btn
                                            @click="goToOrderFormation()"
                                            class=" text-none text-subtitle"
                                            light
                                            rounded="2"
                                            width="100%"
                                            target="_blank"
                                            variant="flat"
                                            color="#D3CDBD"
                                            x-large
                                            density="compact"
                                            >Перейти к оформлению</v-btn>
                                        </v-col>
                                    </v-row>
                                </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
                <v-row v-if="update_total_price(cart) == 0 || cart.length == 0">   
                     <v-card class="mx-auto" max-width="80%" color="black">   
                        <v-row><br><br><br><br></v-row>
                        <v-row>
                            <v-col>
                            <v-img class="shopping_image"
                                    :src="require('../assets/shopping.svg')"
                            />
                            </v-col>
                        </v-row>
                         <v-row>
                            <v-col>
                                <v-text class="item_title_of_dish_cart">В Вашей корзине пока пусто</v-text>
                            </v-col>
                        </v-row>
                     </v-card>                   
                </v-row>
            </v-card>
            <!-- <v-row v-if="order_placement & !profile">
                <v-col cols="1">
                </v-col>
                <v-col cols="3">
                    <v-row>
                        <v-col>
                            <v-card color="black" class="mx-auto" dark>
                                    <v-card-title class="order_title ma-2">КОНТАКТНЫЕ ДАННЫЕ</v-card-title>
                                    <v-card-subtitle class="subtitle_personal_data ma-2">*Обязательные поля</v-card-subtitle>
                                    <v-card-text>
                                        <v-card color="black" dark >
                                        <form class="mx-auto" color="#D3CDBD">
                                        <v-text-field v-model="user.name"
                                            :counter="100"
                                            clearable
                                            :rules="NameRules"
                                            label="Полное имя*"
                                            base-color="#D3CDBD"
                                            color="#D3CDBD"                                            
                                            ></v-text-field>
                                        <v-text-field v-model="user.phone_number"
                                            placeholder="7777777777"
                                            :counter="10"
                                            prefix="+7"
                                            :rules="PhoneRules"
                                            label="Номер телефона*"
                                            color="#D3CDBD"
                                            base-color="#D3CDBD"
                                            clearable
                                            ></v-text-field>
                                        <v-text-field v-model="user.email"
                                            label="Электронная почта"
                                            type="email"
                                            disabled
                                            :counter="100"
                                            :rules="EmailRules"
                                            color="#D3CDBD"
                                            base-color="#D3CDBD"
                                            clearable
                                            ></v-text-field>
                                        </form>
                                        </v-card>
                                    </v-card-text>
                            </v-card>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col>
                            <v-card color="black" class="mx-auto" dark>
                                    <v-card-title class="order_title ma-2">АДРЕС ДОСТАВКИ</v-card-title>
                                    <v-card-text>
                                        <v-card color="black" dark >
                                        <form class="mx-auto" color="#D3CDBD">
                                        <v-text-field v-model="user.address.street"
                                            :counter="100"
                                            clearable
                                            class="ma-2"
                                            :rules="StreetRules"
                                            label="Улица*"
                                            color="#D3CDBD"  
                                            base-color="#D3CDBD"                                          
                                            ></v-text-field>
                                            <v-container>
                                                <v-row>
                                                    <v-col
                                                    cols="12"
                                                    md="4"
                                                    >
                                                    <v-text-field v-model="user.address.house"
                                                        placeholder="1"
                                                        :counter="10"
                                                        :rules="HouseRules"
                                                        label="Дом*"
                                                        color="#D3CDBD"
                                                        base-color="#D3CDBD"
                                                        clearable
                                                        ></v-text-field>
                                                    </v-col>
                                                    <v-col
                                                    cols="12"
                                                    md="4"
                                                    >
                                                    <v-text-field v-model="user.address.corpus"
                                                        label="Корпус"
                                                        type="number"
                                                        :counter="10"
                                                        :rules="CorpusRules"
                                                        color="#D3CDBD"
                                                        base-color="#D3CDBD"
                                                        clearable
                                                        ></v-text-field>
                                                    </v-col>
                                                    <v-col
                                                    cols="12"
                                                    md="4"
                                                    >
                                                    <v-text-field v-model="user.address.flat"
                                                        label="Квартира"
                                                        type="number"
                                                        :counter="10"
                                                        :rules="FlatRules"
                                                        color="#D3CDBD"
                                                        base-color="#D3CDBD"
                                                        clearable
                                                        ></v-text-field>
                                                    </v-col>
                                                </v-row>
                                                </v-container>
                                        </form>
                                        </v-card>
                                    </v-card-text>
                            </v-card>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col>
                            <v-card color="black" class="mx-auto" dark>
                                    <v-card-title class="order_title ma-2">КОММЕНТАРИЙ КУРЬЕРУ</v-card-title>
                                    <v-card-text>
                                        <v-card color="black" dark >
                                        <form class="mx-auto" color="#D3CDBD">
                                        <v-text-field v-model="user.courier_comment"
                                            :counter="100"
                                            clearable
                                            :rules="CommentRules"
                                            label="Написать"
                                            color="#D3CDBD"  
                                            base-color="#D3CDBD"                                          
                                            ></v-text-field>
                                        </form>
                                        </v-card>
                                    </v-card-text>
                            </v-card>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col>
                            <v-card color="black" class="mx-auto" dark>
                                    <v-card-title class="order_title ma-2">СПОСОБ ОПЛАТЫ</v-card-title>
                                    <v-card-text>
                                        <v-card color="black" dark >
                                        <v-radio-group v-model="user.payment_method">
                                            <v-radio color="#D3CDBD" label="Оплата картой" value="1"></v-radio>
                                            <v-radio color="#D3CDBD" label="Оплата наличными" value="2"></v-radio>
                                        </v-radio-group>
                                        </v-card>
                                    </v-card-text>
                            </v-card>
                        </v-col>
                    </v-row>
                </v-col>
                <v-divider color="#D3CDBD" class="mt-2 mb-2" vertical></v-divider>
                    <v-col cols="4">
                        <v-card color="black" class="mx-auto">
                                    <v-card-title class="order_title">ВАШ ЗАКАЗ</v-card-title>
                                    <v-card-text>
                                        <v-row v-for="item in order.items"
                                            :key="item"
                                            md="4">
                                            <v-col>
                                                <v-card color="black" >
                                                    <v-row>
                                                        <v-col cols="2">
                                                            <v-img id="item_image" 
                                                                :src="require('../assets/' + item.image + '.png')"
                                                                />
                                                        </v-col>
                                                        <v-col cols="7">
                                                            <v-text class="item_title_of_dish" >{{item.name}}</v-text>
                                                        </v-col>
                                                        <v-col cols="1"> 
                                                            <v-text class="order_title">{{item.count}}</v-text>
                                                        </v-col>
                                                        <v-col cols="2">
                                                            <v-row>
                                                            <v-col>
                                                            <v-text class="item_title_of_dish">{{item.price}} ₽</v-text>
                                                             </v-col>
                                                            </v-row>
                                                            <v-row>
                                                            <v-col>
                                                            <v-text class="order_title">{{item.count * item.price}} ₽</v-text>
                                                             </v-col>
                                                            </v-row>
                                                        </v-col>
                                                    </v-row>
                                                    <v-divider color="#D3CDBD" class="mt-2 mb-2"></v-divider>
                                                </v-card>
                                            </v-col>
                                        </v-row>
                                    </v-card-text>
                                    </v-card>
                    </v-col>
                    <v-divider color="#D3CDBD" class="mt-2 mb-2" vertical></v-divider>
                    <v-col cols="4">
                        <v-card class="mx-auto" max-width="70%" color="black" dark>
                            <v-card-title class="order_title">СУММА ЗАКАЗА</v-card-title>
                                <v-divider color="#D3CDBD" class="mt-9 mb-8"></v-divider>
                                <v-card-text>
                                    <v-row>
                                        <v-col cols="9">
                                            <v-text class="order_title">Товары в заказе</v-text>
                                        </v-col>
                                        <v-col cols="3">
                                            <v-text class="order_title">{{update_total_price(order)}} ₽</v-text>
                                        </v-col>
                                    </v-row>
                                <v-row>
                                        <v-col cols="9">
                                            <v-text class="order_title">Доставка</v-text>
                                        </v-col>
                                        <v-col cols="3">
                                            <v-text class="order_title">{{delivery_price}} ₽</v-text>
                                        </v-col>
                                    </v-row>
                                </v-card-text>
                                <v-divider color="#D3CDBD" class="mt-8 mb-8"></v-divider>
                                <v-card-text>
                                    <v-row>
                                        <v-col cols="9">
                                            <v-text class="order_title bold">Итого</v-text>
                                        </v-col>
                                        <v-col cols="3">
                                            <v-text class="order_title">{{update_total_price(order) + delivery_price}} ₽</v-text>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col>
                                        <v-btn
                                            @click="order_placement_done = true, order_placement = false"
                                            class=" text-none text-subtitle"
                                            light
                                            rounded="2"
                                            width="100%"
                                            target="_blank"
                                            variant="flat"
                                            color="#D3CDBD"
                                            x-large
                                            density="compact"
                                            >Оформить заказ</v-btn>
                                        </v-col>
                                    </v-row>
                                </v-card-text>
                        </v-card>
                    </v-col>
            </v-row> -->
            <!-- <v-row v-if="order_placement_done">
                <v-card class="mx-auto" max-width="80%" color="black">
                    <br><br>
                    <v-card-title class="title_order_placement text-h6 text-md-h5 text-lg-h4">Ваш заказ №{{order.id}} успешно оформлен!</v-card-title>   
                    <v-card-text>
                        <br>
                            <v-row>
                                <v-col>
                                    <v-text class="subtitle_order_placement">На вашу электронную почту было отправлено письмо с подтверждением. </v-text>
                                </v-col>
                            </v-row>
                            
                            <v-row><v-col></v-col></v-row>
                            <v-row><v-col></v-col></v-row>
                            <v-row>
                                <v-col>
                                <v-img class="shopping_image"
                                        :src="require('../assets/status1.svg')"
                                />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text class="subtitle_order_placement">Информация о заказе доступна в "Профиле" в разделе "Текущие заказы".</v-text>
                                </v-col>
                            </v-row>
                            <v-row><v-col></v-col></v-row>
                            <v-row><v-col></v-col></v-row>
                            <v-row><v-col></v-col></v-row>
                            <v-row><v-col></v-col></v-row>  
                            <v-row>
                                        <v-col>
                                        <v-btn
                                            @click="goToHome()"
                                            class=" text-none text-subtitle"
                                            light
                                            rounded="2"
                                            width="100%"
                                            target="_blank"
                                            variant="flat"
                                            color="#D3CDBD"
                                            x-large
                                            density="compact"
                                            >Вернуться в меню</v-btn>
                                        </v-col>
                                    </v-row>
                            </v-card-text>
                        </v-card>    
            </v-row> -->
           </v-card>
        </v-row>    
</v-app>

</template>

<script>
import axios from 'axios';

export default {    
    name: 'ShoppingCart_',
    
    data () {
      return {
        stock_quantity: 20,
        user: {
            name: "Иван Иванов",
            phone_number: "7777777777",
            email: "ivanivanov@gmail.com",
            address: {
                street: "Студенческая улица",
                house: 33,
                corpus: 3,
                flat: '',
            },
            courier_comment: "",
            payment_method: "1",
        },
        
                delivery_price: 500,
        // cart: [],
            // order  {
            // id: 5,
            // created_at: new Date(2025, 0, 17, 19, 5, 0, 0),
            // status_id: 1,
            // phone_number: "7777777777",
            // address: "Студенческая улица, 33к3",
            // courier_comment: "",
            // payment_method: 2,
            // items: [
            // { 
            // imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad3.png",
            // name: 'Салат с олениной, брусникой и чипсами из топинамбура',
            // count: 1,
            // price: 1500,
            // stock_quantity: 10,
            // },
            // {
            // imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack4.png",
            // name: 'Паштет из утки',
            // price: 900,
            // count: 2,
            // stock_quantity: 14,
            // }
            // ],
            //  },
        //  order: {},
            cart: [],
      }
    },
    
    methods: {
        update_total_price(order){
                let total_price_of_order = 0;
                for (let x of order) {
                    total_price_of_order += x.price * x.quantity;
                }
                console.log(total_price_of_order, this.cart.length)
                return total_price_of_order;
                },
        delete_cart_item(item_id){
            try {
              axios.delete(`http://localhost:8080/api/v1/cart/items/${item_id}`, {
                userId: this.userId
                })
            
            } catch(error) {
              console.log(error);
            }
        },
        get_cart(){
            try {
            axios.get(`http://localhost:8080/api/v1/user/${this.userId}/cart`)
            .then((response) => {
                let cartData = response.data;
                this.cart = [];
                for(let i = 0; i < cartData.data.length; i += 1){
                    this.cart.push(cartData.data[i])
                }
                // let allItems = response.data
                // this.cart = []
                // for(let item in allItems){
                //     this.cart.push(allItems[item])
                // }
                // this.id = id
                    // this.$router.push('/login');
            }) // aleshabulanov@mail.ru
            } catch(error) {
              console.log(error);
            }
        },
         goToOrderFormation() {
            this.$router.push('/formation')
        },
        goToHome() {
            this.$router.push('/')
        },
    },
    mounted(){
        this.get_cart();
        // setInterval(() => this.get_cart(), 500000); //?????
    },
    // watch:{
    //     $route(){
    //         this.get_cart()
    //     }
    // },
    props: ['userId']
};

</script>

<style lang="scss">
.cart_app{
  font-family: "Lexend Exa", sans-serif;
  // transition: background-color 0.3s ease;  
  width: 100%;
  justify-content: center; 
  flex: wrap;
  flex-direction: row;
  display: flex;

}
.plus_minus_outline {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  color: #D3CDBD;
  width: 40%;
}
.item_title_of_dish_cart {
    font-family: "Lexend Exa", sans-serif;
    letter-spacing: 2px;
    color: #D3CDBD;
    font-size: large;
}
.item_total_price_cart {
    font-family: "Lexend Exa", sans-serif;
    letter-spacing: 2px;
    color: #D3CDBD;
    font-size: x-large;
}
.shopping_image {
    // margin-top: 20%;
    width: 100%;
    height: 100%;
    position: relative;
    background-size: contain;
}
.title_order_placement {
    font-family: "Lexend Exa", sans-serif;
    text-align: center;
    position: center;
    align-self: center;
    display: inline-flex;
    flex-grow: 1;
    margin-left: 11%;
    font-size: x-large;
    letter-spacing: 4px;
    color: #D3CDBD;
}
.subtitle_order_placement {
    font-family: "Lexend Exa", sans-serif;
    text-align: center;
    position: center;
    align-self: center;
    display: inline-flex;
    // flex-grow: 0;
    // margin-left: 10%;
    font-size: large;
    letter-spacing: 2px;
    color: #D3CDBD;
}
.subtitle_personal_data {
    font-family: "Lexend Exa", sans-serif;
    color: #D3CDBD;
    letter-spacing: 2px;
}
</style>