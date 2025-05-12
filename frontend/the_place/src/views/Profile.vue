<template>
<v-app class="profile_app"
        app 
        id="app"
        dark>
        <v-row>
           <v-card color="black" width="100%">
            <v-card dark color="black" class="mt-4">
                <v-row>                    
                    <v-card-title class="title text-h6 text-md-h5 text-lg-h4">ПРОФИЛЬ</v-card-title>
                </v-row>
                <v-row> 
                    <v-col cols="4">
                        <v-card class="mx-auto" max-width="70%">
                          <v-list color="black" >
                            <v-list-item>
                                <v-btn v-if="!personal_data"
                                       class="list_items text-none text-subtitle" 
                                       color="#D3CDBD" 
                                       outlined
                                       @click="current_order = false, personal_data = true, completed_order = false, get_user()"
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       density="default"
                                       rounded="2"
                                       >
                                <v-list-item-title class="item_title">
                                    Личные данные
                                </v-list-item-title>
                                </v-btn>
                                <v-btn v-if="personal_data & !current_order & !completed_order"
                                       class="list_items text-none text-subtitle" 
                                       color="#D3CDBD" 
                                       light
                                       @click="personal_data = true"
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       density="default"
                                       rounded="2"
                                       >
                                <v-list-item-title class="item_title">
                                    Личные данные
                                </v-list-item-title>
                                </v-btn>
                            </v-list-item>
                            <v-list-item>
                                <v-btn  v-if="!current_order"
                                       class="list_items text-none text-subtitle" 
                                       color="#D3CDBD" 
                                       outlined
                                       @click="current_order = true, personal_data = false, completed_order = false, change_password = false"
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2"
                                       >
                                <v-list-item-title class="item_title">
                                    Текущие заказы
                                </v-list-item-title>
                                </v-btn>
                                <v-btn  v-if="!personal_data & current_order & !completed_order"
                                       class="list_items text-none text-subtitle" 
                                       color="#D3CDBD" 
                                       light
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2"
                                       >
                                <v-list-item-title class="item_title">
                                    Текущие заказы
                                </v-list-item-title>
                                </v-btn>
                            </v-list-item>
                            <v-list-item>
                                <v-btn v-if="!completed_order"
                                       class="list_items text-none text-subtitle" 
                                       color="#D3CDBD" 
                                       outlined
                                       @click="current_order = false, personal_data = false, completed_order = true, change_password = false"
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2"
                                       >
                                <v-list-item-title class="item_title">
                                    Завершенные заказы
                                </v-list-item-title>
                                </v-btn>
                                <v-btn v-if="!personal_data & !current_order & completed_order"
                                       class="list_items text-none text-subtitle" 
                                       color="#D3CDBD" 
                                       light
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2"
                                       >
                                <v-list-item-title class="item_title">
                                    Завершенные заказы
                                </v-list-item-title>
                                </v-btn>
                            </v-list-item>
                            <v-divider></v-divider>
                            <v-list-item>
                                <v-btn class="list_items text-none text-subtitle" 
                                       color="#D3CDBD" 
                                       light
                                       @click="authorised = false"
                                       x-large 
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2"
                                       >
                                    Выйти
                                </v-btn>
                            </v-list-item>
                          </v-list>
                           
                        </v-card>
                    </v-col>
                    <v-divider vertical color="#D3CDBD"></v-divider>
                    <v-col cols="8">
                         <v-card v-if="personal_data & !change_password"
                                    color="black" 
                                    class="profile_card mx-auto" 
                                    max-width="85%" 
                                        >
                         <v-container>
                            <v-row>
                                <v-col cols="5">
                                </v-col>
                                <v-col cols="7">
                                  <img 
                                    alt="profile"
                                    class="img_profile shrink mr-2 ml-2"
                                    contain
                                    src="../assets/profile.svg"
                                    width="80px"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12">
                                <form class="mx-auto" >
                                    <v-text-field v-model="user.name"
                                                  fast-fail
                                                  :counter="100"
                                                  :rules="[NameRules.required, NameRules.min]"
                                                  label="Полное имя"
                                                  outlined
                                                  base-color="#D3CDBD"
                                                  color="#D3CDBD"
                                                  clearable
                                        >
                                    </v-text-field>
                                    <!--      prefix="+7" -->
                                    <v-text-field v-model="user.phone_number"
                                                  fast-fail
                                                  placeholder="+77777777777"
                                                  :counter="12"
                                                  :rules="[PhoneRules.required, PhoneRules.phone]"
                                                  label="Номер телефона"
                                                  outlined
                                                  color="#D3CDBD"
                                                  base-color="#D3CDBD"
                                                  clearable
                                        >
                                    </v-text-field>
                                    <v-text-field v-model="user.email"
                                                  fast-fail
                                                  :counter="100"
                                                  :rules="[EmailRules.required, EmailRules.min, EmailRules.max, EmailRules.email]"
                                                  disabled
                                                  label="Электронная почта"
                                                  outlined
                                                  color="#D3CDBD"
                                                  base-color="#D3CDBD"
                                                  clearable
                                        >
                                    </v-text-field>
                                    <!-- <v-text-field v-model="user.password_hash"
                                                  fast-fail
                                                  :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                                  :type="show ? 'text' : 'password'"
                                                  @click:append="show = !show"
                                                  password
                                                  :counter="100"
                                                  :rules="PaswordRules"
                                                  label="Пароль"
                                                  outlined
                                                  base-color="#D3CDBD"
                                                  color="#D3CDBD"
                                        >
                                    </v-text-field> -->
                                    <v-btn x-large class="list_items text-none text-subtitle ma-2" 
                                       @click="change_password = true"
                                       color="#D3CDBD" 
                                       light
                                       width="48%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2">
                                        Изменить пароль
                                    </v-btn>
                                    <v-btn x-large class="list_items text-none text-subtitle ma-2" 
                                       @click="save_info_of_profile()"
                                       color="#D3CDBD" 
                                       light
                                       width="48%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2">
                                        Сохранить изменения
                                    </v-btn>
                                </form>
                                </v-col>
                            </v-row>
                         </v-container>
                        </v-card>
                        <v-card v-if="change_password"
                                    color="black" 
                                    class="profile_card mx-auto" 
                                    max-width="85%" 
                                        >
                         <v-container>
                            <v-row>
                                <v-col cols="5">
                                </v-col>
                                <v-col cols="7">
                                  <img 
                                    alt="profile"
                                    class="img_profile shrink mr-2 ml-2"
                                    contain
                                    src="../assets/profile.svg"
                                    width="80px"
                                    />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12">
                                <form class="mx-auto" >
                                    <!-- <v-text-field v-model="user.oldPassword"
                                                  fast-fail
                                                  :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                                  :type="show ? 'text' : 'password'"
                                                  @click:append="show = !show"
                                                  password
                                                  :counter="100"
                                                  :rules="[rules.required, rules.min]"
                                                  label="Текущий пароль"
                                                  underlined
                                                  base-color="#D3CDBD"
                                                  color="#D3CDBD"
                                        >
                                    </v-text-field> -->
                                    <v-text-field v-model="user.newPassword"
                                                  fast-fail
                                                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                                                  :type="show1 ? 'text' : 'password'"
                                                  @click:append="show1 = !show1"
                                                  password
                                                  :counter="100"
                                                  :rules="[rules.required, rules.min]"
                                                  label="Новый пароль"
                                                  underlined
                                                  base-color="#D3CDBD"
                                                  color="#D3CDBD"
                                        >
                                    </v-text-field>
                                    <v-text-field v-model="user.new2Password"
                                                  fast-fail
                                                  :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                                                  :type="show2 ? 'text' : 'password'"
                                                  @click:append="show2 = !show2"
                                                  password
                                                  :counter="100"
                                                  :rules="[rules.required, rules.min, passwordConfirmationRule()]"
                                                  label="Подтвердите новый пароль"
                                                  underlined
                                                  base-color="#D3CDBD"
                                                  color="#D3CDBD"
                                        >
                                    </v-text-field>
                                    <v-row>
                                        <v-col cols="6">
                                    <v-btn x-large v-if="!change_password"
                                       class="list_items text-none text-subtitle ma-2" 
                                       @click="change_password = true"
                                       color="#D3CDBD" 
                                       light
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2">
                                        Изменить пароль
                                    </v-btn>
                                        </v-col>
                                        <v-col cols="6">
                                    <v-btn x-large class="list_items text-none text-subtitle ma-2" 
                                       @click="change_password_in_profile()"
                                       color="#D3CDBD" 
                                       light
                                       width="100%"
                                       target="_blank"
                                       variant="flat"
                                       rounded="2">
                                        Сохранить изменения
                                    </v-btn>
                                        </v-col>
                                    </v-row>
                                </form>
                                </v-col>
                            </v-row>
                         </v-container>
                        </v-card>
                        <v-sheet class="cur_order_card mx-auto" :elevation="20" color="black" border max-width="80%" v-if="current_order">
                            <v-container>
                                <v-row v-for="order in current_orders"
                                            :key="order"
                                            md="4">
                                <v-col>
                                <v-card outlined  color="black" >
                                <v-card-title class="order_title">{{'ЗАКАЗ №' + order.orderId}} 
                                    <v-spacer></v-spacer> 
                                    {{new Date(order.createdAt).getDate()+ " " + months[new Date(order.createdAt).getMonth()] + " " + new Date(order.createdAt).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'})}}
                                    </v-card-title>
                                    <v-card color="black" >
                                    <v-row>
                                        <v-col cols="3">
                                        </v-col>
                                        <v-col cols="9">
                                        <v-img v-if="order.status == statuses[0]"
                                              class="status_image shrink mr-2 ml-2" 
                                             src="../assets/status1.svg"
                                             width="60%"
                                            />
                                        <v-img v-if="order.status == statuses[1]"
                                              class="status_image shrink mr-2 ml-2" 
                                             src="../assets/status2.svg"
                                             width="60%"
                                            />
                                        <v-img v-if="order.status == statuses[2]"
                                              class="status_image shrink mr-2 ml-2" 
                                             src="../assets/status3.svg"
                                             width="60%"
                                            />
                                        <v-img v-if="order.status == statuses[3]"
                                              class="status_image shrink mr-2 ml-2" 
                                             src="../assets/status4.svg"
                                             width="60%"
                                            />
                                        <v-img v-if="order.status == statuses[4]"
                                              class="status_image shrink mr-2 ml-2" 
                                             src="../assets/status5.svg"
                                             width="60%"
                                            />
                                        </v-col>
                                    </v-row>
                                </v-card>
                               <v-row>
                                <v-col cols="7">
                                    <v-card color="black" >
                                    <v-card-subtitle class="order_title">Адрес</v-card-subtitle>
                                    <v-card-text>{{order.address}}</v-card-text>
                                    </v-card>
                                    <v-divider color="#D3CDBD" class="mt-2 mb-2"></v-divider>
                                    <v-card color="black" >
                                    <v-card-subtitle class="order_title">Состав заказа</v-card-subtitle>
                                    <v-card-text>
                                        <v-row v-for="item in order.items"
                                            :key="item"
                                            md="4">
                                            <v-col>
                                                <v-card color="black" >
                                                    <v-row>
                                                        <v-col cols="2">
                                                            <!-- :src="require('../assets/' + item.image + '.png')" -->
                                                            <v-img id="item_image" 
                                                                :src="item.imageUrl"
                                                                />
                                                        </v-col>
                                                        <v-col cols="7">
                                                            <v-text class="item_title_of_dish" >{{item.name}}</v-text>
                                                        </v-col>
                                                        <v-col cols="1"> 
                                                            <v-text class="order_title">{{item.quantity}}</v-text>
                                                        </v-col>
                                                        <v-col cols="2">
                                                            <v-row>
                                                            <v-col>
                                                            <v-text class="item_title_of_dish">{{item.price}} ₽</v-text>
                                                             </v-col>
                                                            </v-row>
                                                            <v-row>
                                                            <v-col>
                                                            <v-text class="order_title">{{item.quantity * item.price}} ₽</v-text>
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
                                <v-col cols="5">
                                    <v-card color="black">
                                        <v-card-title class="order_title">СУММА ЗАКАЗА</v-card-title>
                                        <v-divider color="#D3CDBD" class="mt-9 mb-8"></v-divider>
                                        <v-card-text>
                                            <v-row>
                                                <v-col cols="9">
                                                    <v-text class="order_title">Товары в заказе</v-text>
                                                </v-col>
                                                <v-col cols="3">
                                                    <v-text class="order_title">{{order.totalPrice - 500}} ₽</v-text>
                                                </v-col>
                                             </v-row>
                                           <v-row>
                                                <v-col cols="9">
                                                    <v-text class="order_title">Доставка</v-text>
                                                </v-col>
                                                <v-col cols="3">
                                                    <v-text class="order_title">500 ₽</v-text>
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
                                                    <v-text class="order_title">{{order.totalPrice}} ₽</v-text>
                                                </v-col>
                                             </v-row>
                                        </v-card-text>
                                    </v-card>
                                </v-col>
                               </v-row>
                               <!-- <v-divider color="#D3CDBD" class="mt-8 mb-8"></v-divider> -->
                               <v-divider color="#D3CDBD" class="ma-2"></v-divider>
                                </v-card>
                               </v-col>
                                </v-row>
                            </v-container>
                    </v-sheet>
                        <v-sheet class="profile_card mx-auto" color="black" max-width="80%"  v-if="completed_order">
                            <!-- color="black" -->
                            <v-container>
                                <v-row v-for="comp_order in completed_orders"
                                            :key="comp_order"
                                            md="4">
                                <v-col>
                                <v-card outlined color="black" border-radius="20px">
                                <v-card-title>
                                    <v-text class="comp_order_title">{{'Заказ №' + comp_order.orderId}}</v-text>
                                    <v-spacer></v-spacer> 
                                     <v-text class="comp_order_title">{{comp_order.totalPrice}}  ₽</v-text>
                                    </v-card-title>
                                    <v-card-subtitle>
                                        <v-row>
                                            <v-col cols="10">
                                                <v-text class="comp_order_data">
                                                    {{new Date(comp_order.createdAt).getDate()+ " " + months[new Date(comp_order.createdAt).getMonth()] + " " + new Date(comp_order.createdAt).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'})}}
                                                </v-text>
                                                </v-col>
                                                <v-col cols="2">
                                                <v-text class="comp_order_status">{{comp_order.status}}</v-text>
                                            </v-col>
                                        </v-row>
                                    </v-card-subtitle>
                                    <v-card-text>
                                        <v-sheet color="black" class="sheet_imgs">
                                            <v-row>
                                                <v-col>
                                        <v-slide-group show-arrows>
                                            <v-slide-group-item class="mx-auto" v-for="comp_item in comp_order.items"
                                            :key="comp_item">
                                            <!--    :src="require('../assets/' + comp_item.image + '.png')" -->
                                            <v-img  width="150"
                                                    cover
                                                    border-radius="20px"
                                                    class="comp_order_image ma-2"
                                                    :src="comp_item.imageUrl"
                                                    />
                                            </v-slide-group-item>
                                        </v-slide-group>
                                                </v-col>
                                            </v-row>
                                        </v-sheet>
                                    </v-card-text>
                                </v-card>
                                <v-divider color="#D3CDBD" class="mа-2"></v-divider>
                                </v-col>
                                </v-row>
                            </v-container>
                        </v-sheet>
                    </v-col>
 
                </v-row>
            </v-card>
        </v-card>
        </v-row>
</v-app>
</template>

<script>
import axios from 'axios';

export default {    
    name: 'Profile_',
    
    data () {
      return {
        show: false,
        
        show1: false,
        show2: false,
        rules: {
          required: value => !!value || 'Необходимо заполнить', // Required
          min: v => v.length >= 6 || 'Минимум 8 символов', // Min 8 characters
          emailMatch: () => (`The email and password you entered don't match`),
        },
        months:{
            0: "января",
            1: "февраля",
            2: "марта",
            3: "апреля",
            4: "мая",
            5: "июня",
            6: "июля",
            7: "августа",
            8: "сентября",
            9: "октября",
            10: "ноября",
            11: "декабря",
        },
        statuses: {
            1: "Подтвержден",
            2: "Принят в ресторане",
            3: "Готовится",
            4: "В пути",
            5: "Доставлен"
        },
        statuses2: {
            "Подтвержден": 1,
            "Принят в ресторане" : 2,
            "Готовится": 3,
            "В пути": 4,
            "Доставлен" : 5
        },
        paymentMethods: {
            "Оплата картой" : 1,
            "Оплата наличными" : 2
        },
        user: {
            // name: "Иван Иванов",
            name: "",
            // phone_number: "7777777777",
            phone_number: "",
            // email: "ivanivanov@gmail.com",
            email: "",
            //oldPassword: "",
            newPassword: "",
            new2Password: ""

        },
        current_orders: [
            {
            orderId: 1,
            totalPrice: 3800,
            createdAt: new Date(2025, 0, 17, 19, 5, 0, 0),
            status: "Подтвержден",
            phoneNumber: "7777777777",
            address: "Студенческая улица, 33к3",
            courierComment: "",
            paymentMethod: 1,
            items: [
            { 
            imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad3.png",
            name: 'Салат с олениной, брусникой и чипсами из топинамбура',
            quantity: 1,
            price: 1500,
            }
        ],
        },
      ],
        completed_orders: [],
        // current_orders: [
        //     {
        //     id: 1,
        //     total_price: 3800,
        //     created_at: new Date(2025, 0, 17, 19, 5, 0, 0),
        //     status_id: 1,
        //     phone_number: "7777777777",
        //     address: "Студенческая улица, 33к3",
        //     courier_comment: "",
        //     payment_method: 1,
        //     items: [
        //     { 
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad3.png",
        //     name: 'Салат с олениной, брусникой и чипсами из топинамбура',
        //     count: 1,
        //     price: 1500,
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack4.png",
        //     name: 'Паштет из утки',
        //     price: 900,
        //     count: 2,
        //     },
        // ],
        //     },
        //     {
        //     id: 2,
        //     total_price: 5600,
        //     created_at: new Date(2025, 1, 13, 12, 8, 0, 0),
        //     status_id: 3,
        //     phone_number: "7777777777",
        //     address: "Студенческая улица, 33к3",
        //     courier_comment: "",
        //     payment_method: 2,
        //     items: [
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack1.png",
        //     name: 'Пирожок с томленой говяжей щекой',
        //     price: 600,
        //     count: 2,
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack2.png",
        //     name: 'Рыбный пирог с соусом из одуванчиков',
        //     price: 1300,
        //     count: 1,
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack2.png",
        //     name: 'Намазки из сибирских рыб',
        //     price: 2600,
        //     count: 1,
        //     },
        // ],
        
        //     },
        // ],
        // completed_orders: [
        //     {
        //     id: 3,
        //     total_price: 10765,
        //     created_at: new Date(2025, 0, 17, 19, 5, 0, 0),
        //     status_id: 5,
        //     items: [
        //     { 
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad3.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack1.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack1.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack2.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack3.png",
        //     },
        // ],
        //     },
        //     {
        //     id: 4,
        //     total_price: 15600,
        //     created_at: new Date(2025, 1, 13, 12, 8, 0, 0),
        //     status_id: 5,
        //     items: [
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack4.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack2.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad2.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad1.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack2.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack3.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack1.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad3.png",
        //     },
        // ],
        
        //     },
        //     {
        //     id: 5,
        //     total_price: 7800,
        //     created_at: new Date(2025, 2, 13, 14, 19, 0, 0),
        //     status_id: 5,
        //     items: [
        //     { 
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad1.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack2.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack2.png",
        //     },
        //     {
        //     imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack3.png",
        //     },
        // ],
        //     },
        // ],
        
            personal_data: true,
            current_order: false,
            completed_order: false,
            change_password: false,
            authorised: true,
        
        PaswordRules: { //!!!
        //   required: value => !!value || 'Required.',
        //   min: v => v.length >= 8 || 'Min 8 characters',
        //   emailMatch: () => (`The email and password you entered don't match`),
        },
        EmailRules: {
          required: v => !!v || 'Необходимо заполнить', //E-mail is required
          email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Неверный адрес электронной почты' // Invalid e-mail.
          },
        },
        NameRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Name is required
          min: v => v.length >= 4 || 'Минимум 4 символа', //Min 4 characters
        },
        PhoneRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Phone is required
          phone: value => {
            // const pattern = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{2})[-. ]?([0-9]{2})$/
            const pattern = /^\+7([0-9]{10})$/
            return pattern.test(value) || 'Неверный номер телефона' // 'Invalid phone number'
          },
        },
        }
        
    },
    methods: {
       get_user(){
        // let userId =  this.$route.params.userId;
        // console.log(userId)
        try {
            axios.get(`http://localhost:8080/api/v1/user/${this.userId}`)
            .then((response) => {
                let user_data = response.data.data;
                this.user.email = user_data.email;
                this.user.name = user_data.name;
                this.user.phone_number = user_data.phoneNumber;
            })
            } catch(error) {
              console.log(error);
            }
       },
       get_current_orders(){
        try {
            // let userId =  this.$route.params.userId;
            axios.get(`http://localhost:8080/api/v1/user/${this.userId}/orders?status=active`)
            .then((response) => {
                let orders_data = response.data;
                this.current_orders = []
                 for(let i = 0; i < orders_data.data.length; i += 1){
                    this.current_orders.push(orders_data.data[i])
                 }
                 for (let i in this.current_orders){
                    console.log(this.current_orders[i].orderId);
                    // console.log(this.current_orders[i].orderId);
                 }
              })
            } catch(error) {
              console.log(error);
            }
       },
       get_completed_orders(){
        try {
            // let userId =  this.$route.params.userId;
            this.get_statuses();
            axios.get(`http://localhost:8080/api/v1/user/${this.userId}/orders?status=completed`)
            .then((response) => {
                let orders_data = response.data;
                this.completed_orders = []
                 for(let i = 0; i < orders_data.data.length; i += 1){
                    this.completed_orders.push(orders_data.data[i])
                 }
                 console.log(this.completed_orders);
            
            })
            } catch(error) {
              console.log(error);
            }
       },
        get_statuses(){
        try {
           axios.get('http://localhost:8080/api/v1/order/statuses')
            .then((response) => {
                let status_data = response.data.data;
                this.statuses = [];
                for(let item in status_data){
                    this.statuses.push(status_data[item])
                }
            })
            } catch(error) {
              console.log(error);
            }
       },
        save_info_of_profile(){
        try {
           axios.patch('http://localhost:8080/api/v1/user/edit', {
                userId: this.userId,
                name: this.user.name,
                phoneNumber: this.user.phone_number
           })
           this.get_user();
            } catch(error) {
              console.log(error);
            }
       },
        change_password_in_profile(){
        try {
        if(this.user.newPassword === this.user.new2Password){
           axios.patch('http://localhost:8080/api/v1/user/edit/password', {
                userId: this.userId,
                password: this.user.newPassword
           })}
            } catch(error) {
              console.log(error);
            }
        },
    //    change_status(){

    //    }
       
    },
    computed: {
    passwordConfirmationRule() {
      return () => (this.user.newPassword === this.user.new2Password) || 'Пароли не совпадают' //'Passwords must match'
    }
    },
    mounted(){
        this.get_statuses();
        this.get_current_orders();
        // this.get_completed_orders();
        this.get_user();
        // setInterval(() => this.get_cart(), 500000); //?????
    },
    props:['userId'],
}
    

</script>

<style lang="scss">
.profile_app{
  font-family: "Lexend Exa", sans-serif;
  // transition: background-color 0.3s ease;  
  width: 100%;
  justify-content: center; 
  flex: wrap;
  flex-direction: row;
  display: flex;
}
.list_items {
    font-family: "Lexend Exa", sans-serif;
    letter-spacing: 4px;
    color: #D3CDBD;
}
.item_title {
    text-align: left;
}
.item_title_of_dish {
    font-family: "Lexend Exa", sans-serif;
    // letter-spacing: 4px;
    color: #D3CDBD;
    font-size: small;
}
.order_title {
    font-family: "Lexend Exa", sans-serif;
    letter-spacing: 4px;
    color: rgb(211, 205, 189);
}
.profile_card {
    display: flex;
    // flex-direction: row;
    justify-content: center; 
}
.cur_order_card {
    display: flex;
    justify-content: center; 
    border: #D3CDBD;
}
.item_image {
    max-width: 10%;
    max-height: 10%;
    position: relative;
    background-size: contain;
}

.comp_order_image {
    box-shadow: 0 0 10px #444;
    // max-height: 50%;
    // background-size: contain;
}
.img_profile {
    display: inline-flex;
    text-align: center;
    position: center;
    flex-grow: 1;
    align-self: center;

}
.title {
    font-family: "Lexend Exa", sans-serif;
    text-align: center;
    position: center;
    align-self: center;
    display: inline-flex;
    flex-grow: 1;
    margin-left: 6%;
    font-size: 40px;
    letter-spacing: 4px;
    color: #D3CDBD;
}
.comp_order_title {
    font-family: "Lexend Exa", sans-serif;
    letter-spacing: 4px;
    color: #D3CDBD;
    font-weight: bolder;
}
.comp_order_data {
    font-family: "Lexend Exa", sans-serif;
    letter-spacing: 4px;
    color: #D3CDBD;
    font-weight: lighter;
}
.comp_order_status {
    font-family: "Lexend Exa", sans-serif;
    letter-spacing: 4px;
    color: #D3CDBD;
    font-weight: lighter;
    // margin-left: 68%;
    // text-align: end;
}
</style>