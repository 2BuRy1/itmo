import xmltodict
import yaml
def main():
    # Чтение XML из файла
    with open('schedule.xml', 'r', encoding='utf-8') as xml_file:
        xml_str = xml_file.read()

    # Парсинг XML и преобразование в словарь
    xml_dict = xmltodict.parse(xml_str)

    # Преобразование словаря в YAML и запись в файл
    with open('output.yaml', 'w', encoding='utf-8') as yaml_file:
        yaml.dump(xml_dict, yaml_file, default_flow_style=False, allow_unicode=True)


