import base64

with open("C:/Users/MISA/Desktop/Workspace/S6/ULTIMATE TEAM RACE/server/src/main/resources/static/pdf/CERTIFICATE.jpg", "rb") as image_file:
    base64_string = base64.b64encode(image_file.read()).decode('utf-8')
    print(base64_string)
