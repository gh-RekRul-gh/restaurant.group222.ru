<template>
<v-app class="cart_app"
        app 
        id="app"
        dark>
        <v-row>
           <v-card color="black" width="100%">
            <v-card dark color="black" class="mt-4">
                <v-row>
                    <v-card-title class="title text-h6 text-md-h5 text-lg-h4">ОФОРМЛЕНИЕ</v-card-title>
                </v-row>
         <v-row>
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
                                            :rules="[NameRules.required, NameRules.max]"
                                            label="Полное имя*"
                                            base-color="#D3CDBD"
                                            color="#D3CDBD"                                            
                                            ></v-text-field>
                                        <v-text-field v-model="user.phone_number"
                                            placeholder="7777777777"
                                            :counter="10"
                                            prefix="+7"
                                            :rules="[PhoneRules.required, PhoneRules.phone]"
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
                                            :rules="[EmailRules.required, EmailRules.min, EmailRules.max, EmailRules.email]"
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
                                            :rules="[AddressRules.required, AddressRules.street]"
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
                                                        :rules="[AddressRules.required, AddressRules.house]"
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
                                                        :rules="[AddressRules.corpus]"
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
                                                        :rules="[AddressRules.required, AddressRules.flat]"
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
                                            :rules="[CommentRules.max]"
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
                                                            <!-- :src="require('../assets/' + item.imageUrl + '.png')" -->
                                                            <v-img id="item_image" 
                                                                :src="item.imageUrl"
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
                                            @click="goToPlacedOrder()"
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
            </v-row>
           </v-card>
           </v-card>
        </v-row>
</v-app>

</template>

<script>


export default {    
    name: 'OrderFormation_',
    
    data () {
      return {
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
        update_total_price(order){
                let total_price_of_order = 0;
                for (let x of order.items) {
                    total_price_of_order += x.price * x.count;
                }
                return total_price_of_order;
                },
                delivery_price: 500,
        order: 
             {
            id: 5,
            created_at: new Date(2025, 0, 17, 19, 5, 0, 0),
            status_id: 1,
            phone_number: "7777777777",
            address: "Студенческая улица, 33к3",
            courier_comment: "",
            payment_method: 2,
            items: [
            { 
            imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad3.png",
            name: 'Салат с олениной, брусникой и чипсами из топинамбура',
            count: 1,
            price: 1500,
            stock_quantity: 10,
            },
            {
            imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack4.png",
            name: 'Паштет из утки',
            price: 900,
            count: 2,
            stock_quantity: 14,
            }
            ],
             },

          
        EmailRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Required.
          min: v => v.length >= 8 || 'Минимум 8 символов', //Min 8 characters
          max: v => v.length <= 100 || 'Не более 100 символов', //Max 100 characters
          email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Неверный адрес электронной почты' // Invalid e-mail.
          },
        },
        PhoneRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Phone is required
          phone: value => {
            // const pattern = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{2})[-. ]?([0-9]{2})$/
            const pattern = /^([0-9]{10})$/
            return pattern.test(value) || 'Неверный номер телефона' // 'Invalid phone number'
          },
        },
        NameRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Required.
          // min: v => v.length >= 4 || 'Минимум 4 символа', //Min 4 characters
          max: v => v.length <= 100 || 'Не более 100 символов', //Max 100 characters
        }, 
        CommentRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Required.
          max: v => v.length <= 100 || 'Не более 100 символов', //Max 100 characters
        },
        AddressRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Required.
        //   street: value => {
        //     const pattern = /^()$/
        //     return pattern.test(value) || 'Неверное название улицы' // Invalid ...
        //   },
        //   house: value => {
        //     const pattern = /^()$/
        //     return pattern.test(value) || 'Неверный номер дома' // Invalid ...
        //   },
        //   corpus: value => {
        //     const pattern = /^()$/
        //     return pattern.test(value) || 'Неверный номер корпуса' // Invalid ...
        //   },
        //   flat: value => {
        //     const pattern = /^()$/
        //     return pattern.test(value) || 'Неверный номер квартиры' // Invalid ...
        //   },
        },
      }
    },
    
    methods: {
        goToPlacedOrder() {
            this.$router.push('/placed')
        },
        goToHome() {
            this.$router.push('/')
        },
    }
};
</script>