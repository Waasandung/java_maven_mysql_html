from flask import Flask, jsonify
from flask_cors import CORS
import pymysql


app = Flask(__name__)
CORS(app)
def get_db_connection():
    connection = pymysql.connect(
        host='localhost',       # Database host,localhost equal to 127.0.0.1
        user='root',   # Database user name
        password='AS1305781as', # Database password
        database='VendorPlatform', #Database name
        charset='utf8mb4',     
        cursorclass=pymysql.cursors.DictCursor  
    )
    return connection

#method get,  /api/vendors full address is  http://localhost:PortAdress/api/vendors e.g. http://localhost:5000/api/vendors
#Flask port is defined at the end of the py file
@app.route('/api/vendors', methods=['GET'])
def get_vendors():
    connection = get_db_connection()
    try:
        with connection.cursor() as cursor:
            sql = "SELECT * FROM Vendor" 
            print("SQL Query:", sql) 
            cursor.execute(sql)
            vendors = cursor.fetchall()
            print("Data from database:", vendors)
            json_data = jsonify(vendors)
            print("Json data:", json_data.get_data(as_text=True))
            return json_data 
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    finally:
        connection.close()


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)