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
                                       @click="current_order = false, personal_data = true, completed_order = false"
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
                                       @click="current_order = true, personal_data = false, completed_order = false"
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
                                       @click="current_order = false, personal_data = false, completed_order = true"
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
                         <v-card v-if="personal_data"
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
                                                  :rules="NameRules"
                                                  label="Полное имя"
                                                  outlined
                                                  base-color="#D3CDBD"
                                                  color="#D3CDBD"
                                                  clearable
                                        >
                                    </v-text-field>
                                    <v-text-field v-model="user.phone_number"
                                                  fast-fail
                                                  placeholder="7777777777"
                                                  :counter="10"
                                                  prefix="+7"
                                                  :rules="PhoneRules"
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
                                                  :rules="EmailRules"
                                                  label="Электронная почта"
                                                  outlined
                                                  color="#D3CDBD"
                                                  base-color="#D3CDBD"
                                                  clearable
                                        >
                                    </v-text-field>
                                    <v-text-field v-model="user.password_hash"
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
                                    </v-text-field>
                                    <v-btn x-large class="list_items text-none text-subtitle" 
                                       color="#D3CDBD" 
                                       light
                                       width="50%"
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
                        <v-sheet class="cur_order_card mx-auto" :elevation="20" color="black" border max-width="80%" v-if="current_order">
                            <v-container>
                                <v-row v-for="order in current_orders"
                                            :key="order"
                                            md="4">
                                <v-col>
                                <v-card outlined  color="black" >
                                <v-card-title class="order_title">{{"ЗАКАЗ №" + order.id}} 
                                    <v-spacer></v-spacer> 
                                    {{order.created_at.getDate()+ " " + months[order.created_at.getMonth()] + " " + order.created_at.toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'})}}
                                    </v-card-title>
                                    <v-card color="black" >
                                    <v-row>
                                        <v-col cols="3">
                                        </v-col>
                                        <v-col cols="9">
                                        <v-img v-if="order.status_id == 1"
                                              class="status_image shrink mr-2 ml-2" 
                                             src="../assets/status1.svg"
                                             width="60%"
                                            />
                                        <v-img v-if="order.status_id == 3"
                                              class="status_image shrink mr-2 ml-2" 
                                             src="../assets/status3.svg"
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
                                                    <v-text class="order_title">{{order.total_price - 500}} ₽</v-text>
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
                                                    <v-text class="order_title">{{order.total_price}} ₽</v-text>
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
                                    <v-text class="comp_order_title">{{"Заказ №" + comp_order.id}}</v-text>
                                    <v-spacer></v-spacer> 
                                     <v-text class="comp_order_title">{{comp_order.total_price}}  ₽</v-text>
                                    </v-card-title>
                                    <v-card-subtitle>
                                        <v-row>
                                            <v-col cols="10">
                                                <v-text class="comp_order_data">
                                                    {{comp_order.created_at.getDate()+ " " + months[comp_order.created_at.getMonth()] + " " + comp_order.created_at.toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'})}}
                                                </v-text>
                                                </v-col>
                                                <v-col cols="2">
                                                <v-text class="comp_order_status">{{statuses[comp_order.status_id]}}</v-text>
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
                                            <v-img  width="150"
                                                    cover
                                                    border-radius="20px"
                                                    class="comp_order_image ma-2"
                                                    :src="require('../assets/' + comp_item.image + '.png')"
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

export default {    
    name: 'Profile_',
    
    data () {
      return {
        show: false,
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
        user: {
            name: "Иван Иванов",
            phone_number: "7777777777",
            email: "ivanivanov@gmail.com",
            password_hash: "12345678"
        },
        current_orders: [
            {
            id: 1,
            total_price: 3800,
            created_at: new Date(2025, 0, 17, 19, 5, 0, 0),
            status_id: 1,
            phone_number: "7777777777",
            address: "Студенческая улица, 33к3",
            courier_comment: "",
            payment_method: 1,
            items: [
            { 
            image: "salad3",
            name: 'Салат с олениной, брусникой и чипсами из топинамбура',
            count: 1,
            price: 1500,
            },
            {
            image: "snack4",
            name: 'Паштет из утки',
            price: 900,
            count: 2,
            },
        ],
            },
            {
            id: 2,
            total_price: 5600,
            created_at: new Date(2025, 1, 13, 12, 8, 0, 0),
            status_id: 3,
            phone_number: "7777777777",
            address: "Студенческая улица, 33к3",
            courier_comment: "",
            payment_method: 2,
            items: [
            {
            image: "hot_snack1",
            name: 'Пирожок с томленой говяжей щекой',
            price: 600,
            count: 2,
            },
            {
            image: "hot_snack2",
            name: 'Рыбный пирог с соусом из одуванчиков',
            price: 1300,
            count: 1,
            },
            {
            image: "snack2",
            name: 'Намазки из сибирских рыб',
            price: 2600,
            count: 1,
            },
        ],
        
            },
        ],
        completed_orders: [
            {
            id: 3,
            total_price: 10765,
            created_at: new Date(2025, 0, 17, 19, 5, 0, 0),
            status_id: 5,
            items: [
            { 
            image: "salad3",
            },
            {
            image: "snack1",
            },
            {
            image: "hot_snack1",
            },
            {
            image: "hot_snack2",
            },
            {
            image: "hot_snack3",
            },
        ],
            },
            {
            id: 4,
            total_price: 15600,
            created_at: new Date(2025, 1, 13, 12, 8, 0, 0),
            status_id: 5,
            items: [
            {
            image: "snack4",
            },
            {
            image: "snack2",
            },
            {
            image: "salad2",
            },
            {
            image: "salad1",
            },
            {
            image: "hot_snack2",
            },
            {
            image: "hot_snack3",
            },
            {
            image: "hot_snack1",
            },
            {
            image: "salad3",
            },
        ],
        
            },
            {
            id: 5,
            total_price: 7800,
            created_at: new Date(2025, 2, 13, 14, 19, 0, 0),
            status_id: 5,
            items: [
            { 
            image: "salad1",
            },
            {
            image: "snack2",
            },
            {
            image: "hot_snack2",
            },
            {
            image: "snack3",
            },
        ],
            },
        ],
        
            personal_data: true,
            current_order: false,
            completed_order: false,
            authorised: true,
        
        PaswordRules: { //!!!
        //   required: value => !!value || 'Required.',
        //   min: v => v.length >= 8 || 'Min 8 characters',
        //   emailMatch: () => (`The email and password you entered don't match`),
        },
        EmailRules: { //!!!
          // required: value => !!value || 'Required.',
          // min: v => v.length >= 8 || 'Min 8 characters',
          // emailMatch: () => (`The email and password you entered don't match`),
        },
        NameRules: { //!!!
          // required: value => !!value || 'Required.',
          // min: v => v.length >= 8 || 'Min 8 characters',
          // emailMatch: () => (`The email and password you entered don't match`),
        },
        PhoneRules: { //!!!
          // required: value => !!value || 'Required.',
          // min: v => v.length >= 8 || 'Min 8 characters',
          // emailMatch: () => (`The email and password you entered don't match`),
        },
        }
        
    },
    
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