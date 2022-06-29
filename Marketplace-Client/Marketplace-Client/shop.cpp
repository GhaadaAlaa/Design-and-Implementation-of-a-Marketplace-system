#include "shop.h"
#include "ui_shop.h"
#include "datamanager.h"
#include "itemdet.h"
QList <QString> myList;
SearchQuery sq;
QVector <QPixmap> img;
QVector <QString> names;
QVector <MoneyAmount> prices;

Shop::Shop(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Shop)
{
    ui->setupUi(this);
    dm->getCategories();
    ui->listWidget->addItems(myList);
    sq.categories[0] = ui->listWidget->currentItem()->text();
    sq.maxResults = 10;
    dm->getItemList(sq);

    for(int i=0;i<img.size();i++){
       QLabel* pic = new QLabel(this);
         pic->setPixmap(img[i].scaled(110,110,Qt::KeepAspectRatio));
         ui->gridLayout->addWidget(pic, i,0);
    }

    for(int i=0;i<names.size();i++){
       QLabel* nm = new QLabel(this);
         nm->setText(names[i]);
         ui->gridLayout->addWidget(nm, i,1);
    }

    for(int i=0;i<prices.size();i++){
       QLabel* pr = new QLabel(this);
         pr->setText(QString::number(prices[i].pounds)+"."+QString::number(prices[i].piasters));
         ui->gridLayout->addWidget(pr, i,2);
    }

    for(int i=0;i<names.size();i++){

         QPushButton* but = new QPushButton("view an item");
         ui->gridLayout->addWidget(but, i,3);
    }



}

Shop::~Shop()
{
    delete ui;
}
void Shop::on_but_clicked()
{
  item = new Itemdet(this);
  item->show();
}

void Shop::on_cartButton_clicked()
{

}


void Shop::on_accountButton_clicked()
{

}


void Shop::on_searchButton_clicked()
{

}
void Shop::getCategories_slot(std::vector<QString> result){

    for(int i=0; i < result.size(); i++){
        myList.push_back(result[i]);
    }

}

void getItemList_slot(std::vector<Item> result){
    for(int i=0; i < result.size();i++){
        img[i]=result[i].icon();
         names[i] =result[i].name;
         prices[i]=result[i].price;
    }

}
