#ifndef CART_H
#define CART_H

#include <QDialog>
#include"datamanager.h"
LineEdit* quant;
namespace Ui {
class Cart;
}

class Cart : public QDialog
{
    Q_OBJECT

public:
    explicit Cart(QWidget *parent = nullptr);
    ~Cart();

private slots:
    void on_pushButton_clicked();
    void on_Checkout_clicked();
    void checkout_slot(CheckoutResult result);
    void  getCart_slot (QVector <DetailedCartItem> result);
    void on_quantity_editingFinished();


private:
    Ui::Cart *ui;
};

#endif // CART_H
