<template>
<v-app 
        app 
        id="app"
        dark>
      <v-card color="black" width="100%">
        <v-row>
          <v-col>
        <v-img id="main_image"
          :src="require('../assets/main image.png')"
        />
          </v-col>
        </v-row>
        <v-row>
 <v-card color="black" class="card_tabs">
 <v-card dark>
    <v-tabs 
      v-model="currentItem"
      align-tabs="title"
      color="#D3CDBD"
      background-color="black"
      
      sticky
    >
      <v-tab v-model="currentItem"
            v-for="tabs_item_name in tabs_items"
            @click="currentItem=tabs_item_name.categoryId - 1"
            :key="tabs_item_name"
            :value="tabs_item_name"
            > {{tabs_item_name.name }}
      </v-tab>
    </v-tabs>
 </v-card>
    <v-tabs-window>
      <v-tabs-window-item v-model="currentItem"
                          v-for="tabs_item in tabs_items"
                          :key="tabs_item"
                          :value="tabs_item"> 
        <v-divider class="border-opacity-100 mr-9 ml-9 mt-3" color="#D3CDBD" v-if="((tabs_item.categoryId != 0 & currentItem == tabs_item.categoryId) | (tabs_item.categoryId != 0 & currentItem == 0)) & !check_empty_category(tabs_item)"></v-divider>
        <span class="tab_dish_title center" v-if="((tabs_item.categoryId != 0  & currentItem == tabs_item.categoryId) | (tabs_item.categoryId != 0 & currentItem == 0)) & !check_empty_category(tabs_item)">{{tabs_item.name}}</span>
        <v-divider class="border-opacity-100 mr-9 ml-9" color="#D3CDBD" v-if="((tabs_item.categoryId != 0 & currentItem == tabs_item.categoryId) | (tabs_item.categoryId != 0 & currentItem == 0)) & !check_empty_category(tabs_item)"></v-divider>
        <v-container fluid v-if="((tabs_item.categoryId != 0 & currentItem == tabs_item.categoryId) | (tabs_item.categoryId != 0 & currentItem == 0 )) & !check_empty_category(tabs_item)">
          <v-row>
            <v-col 
              v-for="item in items"
              :key="item"
              md="4"
            >
              <v-card color="black" id="dish" v-if="(tabs_item.categoryId == item.categoryId) | (currentItem == 0 & tabs_item.categoryId == item.categoryId)">
                <v-container>
                <v-row>
                  <!-- :src="require('../assets/' + item.image + '.png')" -->
                <v-img id="dish_image" 
                  :src="item.imageUrl"
                />
                </v-row>
                <v-row>
                  <v-col>
                    <v-row>
                    <span class="dish_name">{{item.name}}</span>
                    </v-row>
                    <v-row>
                      <!-- <v-span class="dish_price" v-if="item.count > 1">{{ item.price * item.count }} ₽</v-span> -->
                      <span class="dish_price">{{ item.price }} ₽</span>

                    </v-row>
                  </v-col>
                  <v-col > 
                     <v-btn v-if="item.count > 0 & authorised()"
                          rounded="l"
                          class="plus_minus text-none text-subtitle ma-2"
                          
                          variant="flat"
                          color="#D3CDBD"
                          size="small"
                          density="compact"
                        >
                        <v-btn v-if="item.count-1 >= 0"
                               icon 
                               target="_blank"
                               @click="item.count--"
                               >
                         <template>
                              <span color="#D3CDBD">–</span>
                          </template>
                        </v-btn>
                        <v-btn v-if="item.count-1 < 0"
                               icon 
                               target="_blank"
                               disabled
                               @click="item.add=!item.add"
                               >
                         <template>
                              <span color="#D3CDBD">–</span>
                          </template>
                        </v-btn>
                        {{item.count}}
                        <v-btn v-if="item.count >= 0 & item.count < stock_quantity"
                               icon 
                               target="_blank"
                               @click="item.count++"
                               >
                        <template>
                          <span color="#D3CDBD">+</span>
                        </template>
                        </v-btn>
                        <v-btn v-if="item.count >= stock_quantity"
                               icon 
                               disabled
                               target="_blank"
                               >
                        <template>
                          <span color="#D3CDBD">+</span>
                        </template>
                        </v-btn>
                        </v-btn>                   
                         <!-- v-if="authorised" -->
                    <v-btn v-if="item.count >= 0 & !authorised()"
                        rounded="l"
                        @click="add_item_to_cart(item.menuItemId, item.count)"
                        class="add text-none text-subtitle ma-2"
                        outlined
                        v-bind="props"
                        target="_blank"
                        variant="flat"
                        color="#D3CDBD"
                        size="small"
                        density="compact"
                      >Добавить
                      <!-- <v-overlay
                              activator="parent"
                              location-strategy="connected"
                              scroll-strategy="none"
                          ><v-card class="pa-2" color="black">
                               <span class="text-subtitle mx-auto ma-2 ml-3 mr-3">Для оформления заказа необходимо авторизоваться.</span>
                            </v-card>
                          </v-overlay> -->
                      </v-btn>
                      <!-- <v-dialog v-model="login_notification_dialog" 
                                width="auto"
                                  >
                          <v-card 
                            color="black"
                            dark
                            width="500"
                          ><v-card color="black" width="100%" height="100%" class="card_support">
                            <v-card-title>
                              <v-btn 
                                  class=" text-none text-subtitle mx-auto ma-2 ml-3 mr-3"
                                  text
                                  rounded="2"
                                  width="80%"
                                  target="_blank"
                                  variant="flat"
                                  color="#D3CDBD"
                                  large
                                  density="compact"
                                  >Уведомление!</v-btn>
                                      
                              <v-btn
                                  @click="login_notification_dialog = false"
                                  class=" text-none text-subtitle mx-auto ma-2"
                                  light 
                                  rounded="2"
                                  icon
                                  target="_blank"
                                  variant="flat"
                                  color="#D3CDBD"
                                  small
                                  density="compact"
                                ><v-img color="#D3CDBD" 
                                      class="ma-2" 
                                      src="../assets/close.svg"/></v-btn>
                            </v-card-title>
                            <v-card-text>
                                    <span class="text-subtitle mx-auto ma-2 ml-3 mr-3">Для оформления заказа необходимо авторизоваться.</span>
                            </v-card-text>
                            </v-card>
                          </v-card>
                      </v-dialog> -->
                      <v-btn v-if="item.count > 0 & authorised()"
                        rounded="xl"
                        @click="item.add=!item.add"
                        class="add text-none text-subtitle"
                        outlined
                        target="_blank"
                        variant="flat"
                        color="#D3CDBD"
                        size="small"
                        density="compact"
                      >Добавить</v-btn>
                       <v-btn v-if="item.count == 0 & authorised()"
                        rounded="xl"
                        @click="item.count++"
                        class="add text-none text-subtitle ma-2"
                        outlined
                        target="_blank"
                        variant="flat"
                        color="#D3CDBD"
                        size="small"
                        density="compact"
                      >Добавить</v-btn>
                      <!-- <v-btn icon v-model="dialog" @click="item.isActive = true">{{item.isActive}}</v-btn>   -->
                      
                   
                  </v-col>
                </v-row>
                </v-container>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
        <v-containr v-if="(tabs_item.categoryId != 0 & currentItem == tabs_item.categoryId) & check_empty_category(tabs_item)">
          <v-row><br><br><br></v-row>
          <v-row>
          <v-col cols="12">
            <v-card class="mx-auto ma-2" max-width="80%" color="black" dark>
              <v-img class="no_dish_image"
                :src="require('../assets/no_dish.svg')"
              />
              <v-card-subtitle class="text_no_dish text-h5 text-md-h4 text-lg-h5 bold">Заходите позже!</v-card-subtitle>
              <v-card-text class="text_no_dish text-h7">Наши повара уже придумывают новые блюда для Вас</v-card-text>
            </v-card>
          </v-col>
        </v-row>
        </v-containr>
      </v-tabs-window-item>
    </v-tabs-window>
  </v-card>
  </v-row>
    </v-card>
</v-app>

</template>

<script>
import axios from 'axios';
  // var add_item = true;
  export default {
    name: 'Main_',
    data () {
      return {
        tab: null,
        currentItem: 0,
        stock_quantity: 20,
        tabs_items: [
          {categoryId: 0, name: 'Все'}, 
          {categoryId: 1, name: 'Салаты'}, 
          {categoryId: 2, name: 'Закуски'}, 
          {categoryId: 3, name: 'Горячие закуски'}, 
          {categoryId: 4, name: 'Супы'}, 
          {categoryId: 5, name: 'Лапша и рис'}, 
          {categoryId: 6, name: 'Сашими'}, 
          {categoryId: 7, name: 'Роллы'}, 
          {categoryId: 8, name: 'Блюда на гриле'}, 
          {categoryId: 9, name: 'Горячие блюда'}, 
          {categoryId: 10, name: 'Десерты'}, 
          {categoryId: 11, name: 'Напитки'}
        ],
        items: [
          {
          menuItemId: 10,
          isActive: false,
          categoryId: 3,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack1.png",
          name: 'Пирожок с томленой говяжей щекой',
          description: '',
          price: 600,
          count: 0,
          add: true,
        },
        {
          menuItemId: 9,
          categoryId: 3,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack2.png",
          name: 'Рыбный пирог с соусом из одуванчиков',
          description: '',
          price: 1300,
          count: 0,
          add: true,
        },
        {
          menuItemId: 8,
          categoryId: 3,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/hot_snack3.png",
          name: 'Хуужууры',
          description: '',
          price: 700,
          count: 0,
          add: true,
        },
         {
          menuItemId: 2,
          categoryId: 1,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad1.png",
          name: 'Зеленый салат с хвойными побегами',
          description: '',
          price: 1000,
          count: 0,
          add: true,
        },
        {
          menuItemId: 3,
          categoryId: 1,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad2.png",
          name: 'Винегрет с мороженым из печеной капусты',
          description: '',
          price: 900,
          count: 0,
          add: true,
        },
        {
          menuItemId: 1,
          categoryId: 1,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/salad3.png",
          name: 'Салат с олениной, брусникой и чипсами из топинамбура',
          description: '',
          price: 1500,
          count: 0,
          add: true,
        },
        {
          menuItemId: 5,
          categoryId: 2,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack1.png",
          name: 'Грузди с луком и сметаной',
          description: '',
          price: 900,
          count: 0,
          add: true,
        },
        {
          menuItemId: 4,
          categoryId: 2,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack2.png",
          name: 'Намазки из сибирских рыб',
          description: '',
          price: 2600,
          count: 0,
          add: true,
        },
        {
          menuItemId: 6,
          categoryId: 2,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack3.png",
          name: 'Намазка из печеного сельдерея',
          description: '',
          price: 800,
          count: 0,
          add: true,
        },
        {
          menuItemId: 7,
          categoryId: 2,
          imageUrl: "https://storage.yandexcloud.net/restaurant.group222.ru/snack4.png",
          name: 'Паштет из утки',
          description: '',
          price: 900,
          count: 0,
          add: true,
        },
        ],
        login_notification_dialog: false,
      }
  },
  methods: {
    authorised() {
       return this.userId != null;
       },
    check_empty_category(category){
        for (let x of this.items) {
             if (x.categoryId == category.categoryId){
              return false;
             }
        }
        return true;
        },
        get_categories(){
          try {
            axios.get('http://localhost:8080/api/v1/categories')
            .then((response) => {
                let allItems = response.data
                this.tabs_items = []
                for(let item in allItems){
                    this.tabs_items.push(allItems[item])
                }
                // this.id = id
                    // this.$router.push('/login');
            })
            } catch(error) {
              console.log(error);
            }
        },
        get_menu(){
        try {
            axios.get('http://localhost:8080/api/v1/menu')
            .then((response) => {
                let allItems = response.data
                this.items = []
                for(let item in allItems){
                    item.push({count: 0, add: true})
                    this.items.push(allItems[item])
                }
                // this.id = id
                    // this.$router.push('/login');
            })
            } catch(error) {
              console.log(error);
            }
        },
        add_item_to_cart(id, count){
          // let item_to_cart;
          // item_to_cart.push({menuItemId: id, quantity: count})
          try {
            axios.post('http://localhost:8080/api/v1/cart/items', this.userId, {menuItemId: id, quantity: count})
            // .then((response) => {})
            } catch(error) {
              console.log(error);
            }
        },
  },
  mounted(){
        this.get_menu();
        this.get_categories();
        setInterval(() => this.get_menu(), 50000);
        setInterval(() => this.get_categories(), 50000); //???????
  },
  watch:{
    $route(){
        this.get_menu()
        this.get_categories()
    }
  },
  props: ['userId'],
  }
</script>

<style lang="scss">
.app {
  font-family: "Lexend Exa", sans-serif;
  // transition: background-color 0.3s ease;
  background: #000 url("../assets/wallpaper.png") no-repeat fixed left bottom;
}
.app.light-theme {
    background-color: #D3CDBD;
    color: #000;
}
.app.dark-theme {
    background-color: #000;
    color: #D3CDBD;
}
.card_tabs{
  background-color: #000;
  color: #D3CDBD;
  width: 80%;
  text-align: center;
  position: center;
  margin-left: 10%;
  margin-top: 2px;
  margin-bottom: 1%;
}
.tabs {
  // border-radius: 10px;
  color:#000;
  margin-top: 5px;
}
.main_image {
    width: 100%;
    height: 100%;
    position: relative;
    background-size: contain;
}
.dish {
  background-color: #000;
  color: #D3CDBD;
  margin-left: 10%;
  margin-top: 10%;
  margin-bottom: 10%;
  margin-right: 10%;
   box-shadow: 0 0 10px #444;
}
.dish_image {
    width: 100%;
    height: 100%;
    position: relative;
    background-size: contain;
}
.dish_name {
  font-family: "Lexend Exa", sans-serif;
  color: #D3CDBD;
  text-align: left;
  margin-left: 10%;
  margin-top: 1%;
  margin-bottom: 1%;
  margin-right: 1%;
}
.dish_price {
  font-family: "Lexend Exa", sans-serif;
  color: #D3CDBD;
  margin-left: 10%;
  margin-top: 1%;
  margin-bottom: 1%;
  margin-right: 1%;
}
.add {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  display: inline-flex;
  flex-grow: 2;
  line-height: 100%;
  // border-radius: 15px;
  align-self: center;
  border-color: #D3CDBD;
}
.plus_minus {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  color: #D3CDBD;
  width: 40%;
}
.tab_dish_title {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  text-transform: uppercase;
  color: #D3CDBD;
}
.fixed-tabs-bar .v-tabs__bar {
  position: -webkit-sticky;
  position: sticky;
  top: 4rem;
  z-index: 2;
}
.no_dish_image{
    margin-left: 40%;
    width: 20%;
    height: 20%;
    position: center;
    background-size: contain;
}
.text_no_dish {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  color: #D3CDBD;
}
</style>
