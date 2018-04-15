import hashlib
import
def hashString(msg, algorithm):
    digest = hashlib.new(algorithm)
    digest.update(msg)
    return digest.hexdigest()

def hashAFile(fileName, algorithm):
    """
    Hash the contents of the file, filename using algorithm, and return its hash value as a hex string.
    """""
    digest = hashlib.new(algorithm)
    with open(fileName, "rb") as inStream:
        #read entire file into buffer
        #WARNING: watch for HUGE files!
        buffer = inStream.read()
        digest.update(buffer)
    return digest.hexdigest()

def hashAHugeFile(fileName, algorithm):
    """
    Hash the contents of the file, filename using algorithm, and return its hash value as a hex string.
    """""
    digest = hashlib.new(algorithm)
    bufferSize = 1024
    with open(fileName, "rb") as inStream:
        buffer = inStream.read(bufferSize) #read bufferSize bytes
        while len(buffer):
            digest = update(buffer) #add to digest
            buffer = inStream.read(bufferSize)
        digest.update(buffer)
    return digest.hexdigest()

def main():
    msg = b"It was the best of times, it was the worst of times.\n"
    hashValue = hashString(msg, "SHA1")
    print(hashValue, "  ", msg)

main()