CREATE PROCEDURE get_user_and_orders(IN user_id_param INT)
BEGIN
    SELECT * FROM user WHERE id = user_id_param;
    SELECT * FROM `order` WHERE user_id = user_id_param;
END;
