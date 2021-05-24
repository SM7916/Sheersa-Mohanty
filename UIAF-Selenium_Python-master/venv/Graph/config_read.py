import  os
def configRead(k):
    file=os.getcwd()+'\\Graph\\config.properties'
    separator = "="
    keys = {}

    # I named your file conf and stored it
    # in the same directory as the script

    with open(file) as f:

        for line in f:
            if separator in line:
                # Find the name and value by splitting the string
                name, value = line.split(separator, 1)

                # Assign key value pair to dict
                # strip() removes white space from the ends of strings
                keys[name.strip()] = value.strip()

    return (keys[k])
